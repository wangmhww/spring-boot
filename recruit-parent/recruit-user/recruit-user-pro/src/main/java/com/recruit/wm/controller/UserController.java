package com.recruit.wm.controller;

<<<<<<< HEAD
import com.recruit.wm.bloomfilter.UserRedisBloomFilter;
import com.recruit.wm.model.User;
import com.recruit.wm.service.IUserService;
import com.recruit.wm.util.RedisOps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
=======
import com.recruit.wm.config.Appconfig;
import com.recruit.wm.model.User;
import com.recruit.wm.service.IUserService;
import lombok.val;
>>>>>>> b7dcbfd3f453400833ebe69c60834457de5e5de7
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

import java.util.List;

/**
 * @author wangmingf
 * @Date Create in 18:01 2020/12/3
 * @since 1.2.0
 */
@Api("用户配置类")
@RestController
<<<<<<< HEAD
@RequestMapping("/api/recruit/user")
@Slf4j
public class UserController {
=======
@RequestMapping("/api/user")
public class UserController implements ImportBeanDefinitionRegistrar {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
>>>>>>> b7dcbfd3f453400833ebe69c60834457de5e5de7

    @Autowired
    public RedisOps redisOps;

    @Autowired
    public UserRedisBloomFilter userRedisBloomFilter;

    @Autowired
    public IUserService userService;

    @PostMapping("/insert")
    @ApiOperation("新增用户接口")
    public int insert(@RequestBody User user) {
        int i = userService.insert(user);
        if(i == 1){
            userRedisBloomFilter.put("User:"+user.getPhone());
            redisOps.setObject("User:"+user.getPhone(), user);
        }
        return i;
    }


    @GetMapping(value = "/selectByPhone/{phone}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value="查询用户", notes="根据指定用户手机号码获取用户信息",httpMethod = "GET")
    @ApiImplicitParam(name = "phone", value = "用户手机号码", required = true,paramType = "path")
    public User selectByPhone(@PathVariable("phone") String phone){
        User user = null;
        if (userRedisBloomFilter.isExist("User:"+phone)){
            user = (User) redisOps.getObject("User:"+phone,User.class);
            if (user == null){
                user = userService.selectByPhone(phone);
                if(user != null){
                    redisOps.setObject("User:"+phone,user);
                }
            }
        } else {
            log.debug("id为${}的用户不存在",phone);
        }
        return user;
    }

    @PostMapping(value="/delete")
    @ApiOperation(value="删除用户", notes = "根据用户手机号码删除用户信息", httpMethod = "POST")
    @ApiImplicitParam(name = "phone",value = "用户手机号码", required = true)
    public int deleteByPhone(@Param("phone") String phone){
        return userService.deleteByPhone(phone);
    }

    @PostMapping(value="/selectUserList")
    @ApiOperation(value="查询用户信息列表", notes = "根据参数查询用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "用户手机号码", dataType = "String" ,required = false),
            @ApiImplicitParam(name = "name",value = "用户姓名",dataType = "String" , required = false)
    })
    public List<User> selectUserList(String phone, String name) {
        User user = new User();
        if(phone != null && !"".equals(phone)){
            user.setPhone(phone);
        }
        if(name != null  && !"".equals(name)){
            user.setName(name);
        }
        return userService.selectUserList(user);
    }

}
