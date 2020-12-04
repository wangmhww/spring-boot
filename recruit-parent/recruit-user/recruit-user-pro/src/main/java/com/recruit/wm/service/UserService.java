package com.recruit.wm.service;

import com.recruit.wm.dao.UserDao;
import com.recruit.wm.model.User;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangm
 * @title: UserService
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/321:38
 */
@Service
public class UserService implements IUserService , BeanNameAware {

    @Autowired
    public UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }


    @Override
    public void setBeanName(String s) {
        System.out.println(s);

    }
}
