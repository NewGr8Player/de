package com.sxkj.de.service;

import com.sxkj.de.bean.ScheduledTask;
import org.junit.Test;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.HashMap;

import static org.junit.Assert.*;

public class QuartzManagerTest {

    @Autowired
    private QuartzManager quartzManager;

    @Test
    public void addJob() {
        ScheduledTask scheduledTask = new ScheduledTask("1","1","1","* * * * * ?","http://www.weather.com.cn/data/cityinfo/101010100.html","1");
        quartzManager.addJob(ScheduledTask.class, scheduledTask.getTaskKey(), scheduledTask.getTaskKey(), scheduledTask.getTaskCron(), new JobDataMap(new HashMap<>()));
    }

    @Test
    public void addJob1() {
    }

    @Test
    public void addJob2() {
    }

    @Test
    public void updateJob() {
    }

    @Test
    public void deleteJob() {
    }

    @Test
    public void pauseJob() {
    }

    @Test
    public void resumeJob() {
    }

    @Test
    public void runAJobNow() {
    }

    @Test
    public void queryAllJob() {
    }

    @Test
    public void queryRunJon() {
    }
}