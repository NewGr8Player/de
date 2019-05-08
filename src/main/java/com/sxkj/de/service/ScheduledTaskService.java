package com.sxkj.de.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxkj.de.bean.ScheduledTask;
import com.sxkj.de.dao.ScheduledTaskDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定时任务实现
 */
@Slf4j
@Service
public class ScheduledTaskService {

    @Autowired
    private ScheduledTaskDao scheduledTaskDao;
    /**
     * 可重入锁
     */
    private ReentrantLock lock = new ReentrantLock();
    /**
     * 定时任务线程池
     */
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    /**
     * 所有定时任务存放Map
     * key :任务 key
     * value:任务实现
     */
    @Autowired
    @Qualifier(value = "scheduledTaskJobMap")
    private Map<String, ScheduledTaskJob> scheduledTaskJobMap;

    /**
     * 存放已经启动的任务map
     */
    private Map<String, ScheduledFuture> scheduledFutureMap = new ConcurrentHashMap<>();

    /**
     * 所有任务列表
     */
    public List<ScheduledTask> taskList() {
        List<ScheduledTask> taskBeanList = scheduledTaskDao.selectList(new QueryWrapper<>(new ScheduledTask().initStartFlag("1")));
        if (CollectionUtils.isEmpty(taskBeanList)) {
            return new ArrayList<>();
        }

        return taskBeanList;
    }


    /**
     * 根据任务key 启动任务
     */
    public Boolean start(String taskKey) {
        log.info(">>>>>> 启动任务 {} 开始 >>>>>>", taskKey);
        //添加锁放一个线程启动，防止多人启动多次
        lock.lock();
        log.info(">>>>>> 添加任务启动锁完毕");
        try {
            //校验是否已经启动
            if (this.isStart(taskKey)) {
                log.info(">>>>>> 当前任务已经启动，无需重复启动！");
                return false;
            }
            //校验任务是否存在
            if (!scheduledTaskJobMap.containsKey(taskKey)) {
                return false;
            }
            //根据key数据库获取任务配置信息
            ScheduledTask scheduledTask = scheduledTaskDao.selectOne(new QueryWrapper<>(new ScheduledTask().taskKey(taskKey)));
            //启动任务
            this.doStartTask(scheduledTask);
        } finally {
            // 释放锁
            lock.unlock();
            log.info(">>>>>> 释放任务启动锁完毕");
        }
        log.info(">>>>>> 启动任务 {} 结束 >>>>>>", taskKey);
        return true;
    }

    /**
     * 根据 key 停止任务
     */
    public Boolean stop(String taskKey) {
        log.info(">>>>>> 进入停止任务 {}  >>>>>>", taskKey);
        //当前任务实例是否存在
        boolean taskStartFlag = scheduledFutureMap.containsKey(taskKey);
        log.info(">>>>>> 当前任务实例是否存在 {}", taskStartFlag);
        if (taskStartFlag) {
            //获取任务实例
            ScheduledFuture scheduledFuture = scheduledFutureMap.get(taskKey);
            //关闭实例
            scheduledFuture.cancel(true);
        }
        log.info(">>>>>> 结束停止任务 {}  >>>>>>", taskKey);
        return taskStartFlag;
    }

    /**
     * 根据任务key 重启任务
     */
    public Boolean restart(String taskKey) {
        log.info(">>>>>> 进入重启任务 {}  >>>>>>", taskKey);
        //先停止
        this.stop(taskKey);
        //再启动
        return this.start(taskKey);
    }

    /**
     * 程序启动时初始化  ==> 启动所有正常状态的任务
     */
    public void initAllTask(List<ScheduledTask> ScheduledTaskList) {
        log.info("程序启动 ==> 初始化所有任务开始 ！size={}", ScheduledTaskList.size());
        if (CollectionUtils.isEmpty(ScheduledTaskList)) {
            return;
        }
        for (ScheduledTask scheduledTask : ScheduledTaskList) {
            //任务 key
            String taskKey = scheduledTask.taskKey();
            //校验是否已经启动
            if (this.isStart(taskKey)) {
                continue;
            }
            //启动任务
            this.doStartTask(scheduledTask);
        }
        log.info("程序启动 ==> 初始化所有任务结束 ！size={}", ScheduledTaskList.size());
    }

    /**
     * 执行启动任务
     */
    private void doStartTask(ScheduledTask scheduledTask) {
        //任务key
        String taskKey = scheduledTask.taskKey();
        //定时表达式
        String taskCron = scheduledTask.taskCron();
        //获取需要定时调度的接口
        ScheduledTaskJob scheduledTaskJob = scheduledTaskJobMap.get(taskKey);
        log.info(">>>>>> 任务 [ {} ] ,cron={}", scheduledTask.taskDesc(), taskCron);
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(scheduledTaskJob,
                (triggerContext) -> {
                    CronTrigger cronTrigger = new CronTrigger(taskCron);
                    return cronTrigger.nextExecutionTime(triggerContext);
                });
        //将启动的任务放入 map
        scheduledFutureMap.put(taskKey, scheduledFuture);
    }

    /**
     * 任务是否已经启动
     */
    private Boolean isStart(String taskKey) {
        //校验是否已经启动
        if (scheduledFutureMap.containsKey(taskKey)) {
            if (!scheduledFutureMap.get(taskKey).isCancelled()) {
                return true;
            }
        }
        return false;
    }

}
