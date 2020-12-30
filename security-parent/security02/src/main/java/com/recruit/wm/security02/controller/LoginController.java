package com.recruit.wm.security02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmingf
 * @Date Create in 10:32 2020/12/29
 * @since 1.2.0
 */
@RestController
@RequestMapping("/admin")
public class LoginController {

    @GetMapping("/login")
    public String admin(){
        return "hello Security Demo";
    }
}
