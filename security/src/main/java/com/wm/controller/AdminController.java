package com.wm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangm
 * @title: AdminController
 * @projectName security
 * @description: TODO
 * @date 2020/12/2522:08
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping("/login")
    public String login(){
        return "hello Spring Security Demo";
    }

    @GetMapping("/index")
    public String index(){
        return "欢迎到index界面" ;
    }
}
