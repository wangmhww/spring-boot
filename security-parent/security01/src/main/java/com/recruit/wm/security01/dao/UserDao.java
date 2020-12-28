package com.recruit.wm.security01.dao;

import com.recruit.wm.security01.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangmingf
 * @Date Create in 9:52 2020/12/28
 * @since 1.2.0
 */
@Mapper
public interface UserDao {

    public User selectUserByName(String name);

}
