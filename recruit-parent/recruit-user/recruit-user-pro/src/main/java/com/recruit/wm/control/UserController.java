package com.recruit.wm.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangmingf
 * @Date Create in 18:01 2020/12/3
 * @since 1.2.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @PostMapping("/insert")
    public void insert(){

    }
}
