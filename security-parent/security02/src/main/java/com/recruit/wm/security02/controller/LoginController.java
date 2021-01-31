package com.recruit.wm.security02.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

/**
 * @author wangmingf
 * @Date Create in 10:32 2020/12/29
 * @since 1.2.0
 */
@RestController
@RequestMapping("/admin")
public class LoginController {

    @GetMapping("/login")
    @RolesAllowed("ROLE_user") //必须是ROLE_开头
//    @Secured("ROLE_admin")  //必须是ROLE_开头
    public String admin() {
        return "hello Security Demo";
    }

    @PreAuthorize("#id<4")
    @GetMapping("/index")
    public String index(int id) {
        return "hello Security Demo";
    }
}
