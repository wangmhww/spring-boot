package com.recruit.wm.dao;

import com.recruit.wm.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangm
 * @title: UserDao
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/321:40
 */
@Mapper
public interface UserDao {
    public int insert(User User);

    public User selectByPhone(String phone);

    public User selectById(String id);

    public int deleteByPhone(String id);

    public List<User> selectUserList(User user);

}
