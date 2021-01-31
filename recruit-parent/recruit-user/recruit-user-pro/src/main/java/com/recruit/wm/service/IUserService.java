package com.recruit.wm.service;

import com.recruit.wm.model.User;

import java.util.List;

/**
 * @author wangmingf
 * @Date Create in 9:14 2020/12/4
 * @since 1.2.0
 */
public interface IUserService {
    public int insert(User user);

    public User selectByPhone(String phone);

    public User selectById(String id);

    public int deleteByPhone(String phone);

    public List<User> selectUserList(User user);
}
