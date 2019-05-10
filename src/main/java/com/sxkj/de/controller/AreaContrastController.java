package com.sxkj.de.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxkj.de.bean.AreaContrast;
import com.sxkj.de.service.AreaContrastService;
import com.sxkj.de.service.SendInfoService;
import com.sxkj.de.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录controller
 *
 * @author NewGr8Player
 */
@Controller
@RequestMapping("/areaContrast")
public class AreaContrastController {

    @Autowired
    private UserService userService;

    @Autowired
    private AreaContrastService areaContrastService;


    /**
     * 主控面板
     *
     * @return
     */
    @GetMapping(path = {"/",""})
    public ModelAndView areaContrastManage(ModelAndView modelAndView) {
        modelAndView.setViewName("areaContrast");
        return modelAndView;
    }
}
