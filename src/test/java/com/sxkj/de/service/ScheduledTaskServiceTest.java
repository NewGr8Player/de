package com.sxkj.de.service;

import com.sxkj.de.DeApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ScheduledTaskServiceTest extends DeApplicationTests {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Test
    public void taskList() {
        scheduledTaskService.taskList().stream().forEach(
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