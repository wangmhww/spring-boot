package com.recruit.wm.redisinit;

import com.recruit.wm.bloomfilter.UserRedisBloomFilter;
import com.recruit.wm.model.User;
import com.recruit.wm.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author wangm
 * @title: UserRedisInit
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/200:57
 */
@Component
@Slf4j
public class UserRedisInit {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRedisBloomFilter userRedisBloomFilter;

    @PostConstruct
    public void init(){
        List<User> userList = userService.selectUserList(new User());
        for (int i = 0; i < userList.size(); i++) {
            userRedisBloomFilter.put("User:"+ userList.get(i).getPhone());
            log.info(userList.get(i).getId() + "添加到布隆过滤器中");
        }
    }
}
