package com.sxkj.de.controller;

import com.sxkj.de.bean.ScheduledTask;
import com.sxkj.de.service.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 定时任务展示Controller
 *
 * @author NewGr8Player
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    /**
     * 定时任务展示列表
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView listAll(ModelAndView modelAndView) {
        modelAndView.addObject("taskList",scheduledTaskService.taskList(new ScheduledTask()));
        modelAndView.setViewName("taskList");
        return modelAndView;
    }
}
