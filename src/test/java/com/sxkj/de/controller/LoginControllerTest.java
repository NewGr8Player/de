package com.sxkj.de.controller;

import com.sxkj.de.DeApplicationTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 登录Controller单元测试
 *
 * @author NewGr8Player
 */
public class LoginControllerTest extends DeApplicationTests {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void loginPage() throws Exception {
        mockMvc.perform
                (
                        get("/login")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("id", "1")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void loginMethod() throws Exception {
        mockMvc.perform
                (
                        post("/login")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("username", "admin")
                                .param("password", "0")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void logoutMethod() throws Exception {
        mockMvc.perform
                (
                        post("/logout")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}