package com.sxkj.de.service;

import com.sxkj.de.DeApplicationTests;
import com.sxkj.de.bean.ScheduledTask;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * 调度任务service单元测试
 *
 * @author NewGr8Player
 */
public class ScheduledTaskServiceTest extends DeApplicationTests {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Test
    public void taskList() {
        scheduledTaskService.taskList(new ScheduledTask()).stream().forEach(
                System.out::println
        );
    }

    @Test
    public void start() {
    }

    @Test
    public void stop() {
    }

    @Test
    public void restart() {
    }

    @Test
    public void initAllTask() {
    }
}