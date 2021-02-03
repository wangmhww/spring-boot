package com.oauth.wm.jwtdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangm
 * @title: Login
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/323:26
 */
@RestController
@RequestMapping("/admin")
public class Login {
    @RequestMapping("/login")
    public String login(){
        return "登录成功";
    }
}
