package com.recruit.wm.security01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmingf
 * @Date Create in 14:03 2020/12/28
 * @since 1.2.0
 */
@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping("/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String invalid(){
        return "session失效";
    }
}
