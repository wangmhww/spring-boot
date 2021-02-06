package com.oauth.wn.oauth2ssoclientdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangm
 * @title: UserController
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/616:15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication){
        return authentication;
    }
}
