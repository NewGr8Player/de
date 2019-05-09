package com.sxkj.de.controller;

import com.sxkj.de.bean.User;
import com.sxkj.de.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * 登录controller
 *
 * @author NewGr8Player
 */
@Controller
@RequestMapping("/control")
public class ControlController {

    @Autowired
    private UserService userService;

    /**
     * 主控面板
     *
     * @return
     */
    @GetMapping(path = {"/",""})
    public ModelAndView controlPage(ModelAndView modelAndView) {
        //modelAndView.addObject("user", new User());
        modelAndView.setViewName("control");
        return modelAndView;
    }
}
