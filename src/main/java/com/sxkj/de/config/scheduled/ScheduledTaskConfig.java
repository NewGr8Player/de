package com.sxkj.de.config.scheduled;

import com.sxkj.de.service.ScheduledTaskJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Map;


/**
 * 定时任务线程池配置
 *
 * @author NewGr8Player
 */
@Slf4j
@Configuration
public class ScheduledTaskConfig {

    @Autowired
    private ScheduledTaskProperties scheduledTaskProperties;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        log.info("创建定时任务调度线程池 start");
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(scheduledTaskProperties.poolSize());
        threadPoolTaskScheduler.setThreadNamePrefix(scheduledTaskProperties.threadNamePrefix());
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(scheduledTaskProperties.waitForTasksToCompleteOnShutdown());
        threadPoolTaskScheduler.setAwaitTerminationSeconds(scheduledTaskProperties.awaitTerminationSeconds());
        log.info("创建定时任务调度线程池 end");
        return threadPoolTaskScheduler;
    }

    /**
     * 初始化定时任务Map(枚举确保唯一)
     *
     * @return
     */
    @Bean(name = "scheduledTaskJobMap")
    public Map<String, ScheduledTaskJob> scheduledTaskJobMap() {
        return ScheduledTaskEnum.initScheduledTask();
    }

}
