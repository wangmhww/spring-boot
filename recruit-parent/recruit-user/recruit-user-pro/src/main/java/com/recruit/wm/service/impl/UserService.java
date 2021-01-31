package com.recruit.wm.service.impl;

import com.recruit.wm.dao.UserDao;
import com.recruit.wm.model.User;
import com.recruit.wm.service.IUserService;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangm
 * @title: UserService
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/321:38
 */
@Service
public class UserService implements IUserService {

    @Autowired
    public UserDao userDao;

    @Override
    public User selectByPhone(String phone){
        return userDao.selectByPhone(phone);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public User selectById(String id) {
        return userDao.selectById(id);
    }

    @Override
    public int deleteByPhone(String phone) {
        return userDao.deleteByPhone(phone);
    }

    @Override
    public List<User> selectUserList(User user) {
        return userDao.selectUserList(user);
    }


}
