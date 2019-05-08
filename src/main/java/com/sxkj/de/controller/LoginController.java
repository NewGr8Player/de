package com.sxkj.de.controller;

import com.sxkj.de.bean.User;
import com.sxkj.de.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转登陆页面
     *
     * @return
     */
    @GetMapping(path = {"/login"})
    public ModelAndView loginPage(ModelAndView modelAndView) {
        modelAndView.addObject("user",new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 登陆方法
     *
     * @param user
     * @return
     */
    @PostMapping(path = {"/login"})
    public ModelAndView loginMethod(ModelAndView modelAndView, User user) {
        if (null != user && StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {
            User dbUser = userService.findByUserName(user.getUsername());
            if(null != dbUser && Objects.equals(dbUser.getPassword(),user.getPassword())){
                modelAndView.setViewName("index");
                modelAndView.addObject("user", dbUser);
            } else {
                modelAndView.setViewName("login");
                modelAndView.addObject("message", "用户名或密码错误");
            }
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "请正确填写用户名与密码");
        }
        return modelAndView;
    }

    /**
     * 注销登录
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(path = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView logoutMethod(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
