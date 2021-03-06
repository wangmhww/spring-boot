package com.oauth.wm.jwtdemo.controller;

import com.oauth.wm.jwtdemo.bean.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangm
 * @title: UserController
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/321:40
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication){
        return authentication;
    }
}
