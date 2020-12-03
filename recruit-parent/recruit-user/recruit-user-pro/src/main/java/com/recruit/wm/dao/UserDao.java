package com.recruit.wm.dao;

import com.recruit.wm.model.User;
import org.apache.ibatis.annotations.Mapper;

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
}
