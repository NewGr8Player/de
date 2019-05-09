package com.sxkj.de.config.init;

import com.sxkj.de.bean.ScheduledTask;
import com.sxkj.de.service.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 初始化运行
 *
 * @author NewGr8Player
 */
@Component
public class InitRunner implements CommandLineRunner, Ordered {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Override
    public int getOrder(){
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void run(String... var1) {
        scheduledTaskService.init();
        scheduledTaskService.startTaskList(scheduledTaskService.taskList(new ScheduledTask().setInitStartFlag("1")));
    }
}
