package com.wm.dao;

import com.wm.bean.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author wangm
 * @title: UserDao
 * @projectName security
 * @description: TODO
 * @date 2020/12/2621:14
 */
@Mapper
public interface UserDao {

    public User getUserByName(String userName);
}
