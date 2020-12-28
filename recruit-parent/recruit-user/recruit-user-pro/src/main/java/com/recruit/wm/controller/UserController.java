package com.recruit.wm.controller;

import com.recruit.wm.config.Appconfig;
import com.recruit.wm.model.User;
import com.recruit.wm.service.IUserService;
import lombok.val;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangmingf
 * @Date Create in 18:01 2020/12/3
 * @since 1.2.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController implements ImportBeanDefinitionRegistrar {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);

    static {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Autowired
    public IUserService userService;

    @PostMapping("/insert")
    public int insert(@RequestBody User user){
        return userService.insert(user);
    }

    @GetMapping(value = "/selectByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User selectById(@Param("name") String name){
        return User.builder().name(name).age(26).build();
    }

}
