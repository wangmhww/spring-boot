package com.wm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangm
 * @title: LoginController
 * @projectName security
 * @description: TODO
 * @date 2020/12/2723:39
 */
@Controller
public class LoginController {

    @RequestMapping("/main")
    public String main(){
        return "redirect:/main.html";
    }

    @RequestMapping("/toerror")
    public String error(){
        System.out.println("toerror");
        return "redirect:/error.html";
    }
}
