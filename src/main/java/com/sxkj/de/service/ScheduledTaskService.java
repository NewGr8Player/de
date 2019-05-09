package com.sxkj.de.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxkj.de.bean.ScheduledTask;
import com.sxkj.de.dao.ScheduledTaskDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定时任务实现
 *
 * @author NewGr8Player
 */
@Slf4j
@Service
@Transactional
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
    public List<ScheduledTask> taskList(ScheduledTask scheduledTask) {
        List<ScheduledTask> taskBeanList = scheduledTaskDao.selectList(new QueryWrapper<>(scheduledTask));
        if (CollectionUtils.isEmpty(taskBeanList)) {
            taskBeanList = new ArrayList<>();
        }
        log.info("初始化任务数量：{}", taskBeanList.size());
        return taskBeanList;
    }


    /**
     * 根据任务key 启动任务
     */
    public Boolean start(String taskKey) {
        log.info("启动任务:{}", taskKey);
        lock.lock();
        log.info("启动锁,{}", taskKey);
        try {
            if (this.isStart(taskKey)) {
                log.info("任务已启动，无需重复启动:{}", taskKey);
                return false;
            }
            if (!scheduledTaskJobMap.containsKey(taskKey)) {
                log.info("任务不存在，无法启动:{}", taskKey);
                return false;
            }
            ScheduledTask scheduledTask = scheduledTaskDao.selectOne(new QueryWrapper<>(new ScheduledTask().setTaskKey(taskKey)));
            this.doStartTask(scheduledTask);
        } finally {
            lock.unlock();
            log.info("释放锁,{}",taskKey);
        }
        return true;
    }

    /**
     * 根据 key 停止任务
     */
    public Boolean stop(String taskKey) {
        log.info("期望停止任务:{}", taskKey);
        //当前任务实例是否存在
        boolean taskStartFlag = scheduledFutureMap.containsKey(taskKey);
        log.info("任务:{},存在:{}", taskKey, taskStartFlag);
        if (taskStartFlag) {
            ScheduledFuture scheduledFuture = scheduledFutureMap.get(taskKey);
            scheduledFuture.cancel(true);
        }
        log.info("停止任务结果:{}", taskKey);
        return taskStartFlag;
    }

    /**
     * 根据任务key 重启任务
     *
     * @param taskKey 任务key
     */
    public Boolean restart(String taskKey) {
        log.info("重启任务:{}", taskKey);
        this.stop(taskKey);
        return this.start(taskKey);
    }

    /**
     * 启动ScheduledTaskList中所有任务
     *
     * @param ScheduledTaskList 任务list
     */
    public void startTaskList(List<ScheduledTask> ScheduledTaskList) {
        log.info("启动任务,数量:{}", ScheduledTaskList.size());
        if (CollectionUtils.isEmpty(ScheduledTaskList)) {
            return;
        }
        for (ScheduledTask scheduledTask : ScheduledTaskList) {
            String taskKey = scheduledTask.getTaskKey();
            if (this.isStart(taskKey)) {
                continue;
            }
            this.doStartTask(scheduledTask);
        }
        log.info("启动完成。");
    }

    /**
     * 查找全部运行中任务
     *
     * @return
     */
    public Map<String, ScheduledFuture> findAllRunningTask() {
        return scheduledFutureMap;
    }

    /**
     * 执行启动任务
     *
     * @param scheduledTask 定时任务Bean
     */
    private void doStartTask(ScheduledTask scheduledTask) {
        String taskKey = scheduledTask.getTaskKey();
        String taskCron = scheduledTask.getTaskCron();
        ScheduledTaskJob scheduledTaskJob = scheduledTaskJobMap.get(taskKey);
        log.info("任务[Key:{}]({}),cron={}", scheduledTask.getTaskKey(), scheduledTask.getTaskDesc(), taskCron);
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(scheduledTaskJob,
                (triggerContext) -> {
                    CronTrigger cronTrigger = new CronTrigger(taskCron);
                    return cronTrigger.nextExecutionTime(triggerContext);
                });
        scheduledFutureMap.put(taskKey, scheduledFuture);
    }

    /**
     * 任务是否已经启动
     *
     * @param taskKey 任务key
     */
    public Boolean isStart(String taskKey) {
        return scheduledFutureMap.containsKey(taskKey) && !scheduledFutureMap.get(taskKey).isCancelled();
    }

    /**
     * 初始化列表
     */
    public void init() {
        List<ScheduledTask> list = taskList(new ScheduledTask());
        if (!CollectionUtils.isEmpty(list)) {
            log.info("初始化大小：{}。", list.size());
            list.forEach(
                    e -> scheduledTaskJobMap.put(e.getTaskKey(), e)
            );
        } else {
            log.info("初始化大小：0。");
        }
    }
}
