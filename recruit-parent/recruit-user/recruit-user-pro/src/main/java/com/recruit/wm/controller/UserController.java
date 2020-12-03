package com.recruit.wm.controller;

import com.recruit.wm.model.User;
import com.recruit.wm.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangmingf
 * @Date Create in 18:01 2020/12/3
 * @since 1.2.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    static {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Autowired
    public IUserService userService;

    @PostMapping("/insert")
    public int insert(@RequestBody User user){
        return userService.insert(user);
    }

    @GetMapping("/selectByName/name")
    @ResponseBody
    public User selectById(@Param("name") String name){
        return User.builder().name(name).age(26).build();
    }
}
