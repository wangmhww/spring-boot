package com.recruit.wm.security01.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmingf
 * @Date Create in 10:06 2020/12/28
 * @since 1.2.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/login")
    public String login(){
        return "hello Security Demo";
    }

    @GetMapping("/index")
    public String  index(){
        String userName = getUserName();
        return userName + "认证成功";
    }

    private String getUserName(){
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();

        String userName = null;
        if(principal instanceof UserDetails){
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
