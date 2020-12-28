package com.recruit.wm.security01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangmingf
 * @Date Create in 13:32 2020/12/28
 * @since 1.2.0
 */
@Controller
public class LoginController {

    @RequestMapping("/main")
    public String main(){
        return "redirect:/main.html";
    }

    @RequestMapping("/toerror")
    public String error(){
        return "redirect:/error.html";
    }
}
