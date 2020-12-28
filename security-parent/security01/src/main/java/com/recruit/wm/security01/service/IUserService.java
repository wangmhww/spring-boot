package com.recruit.wm.security01.service;

import com.recruit.wm.security01.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author wangmingf
 * @Date Create in 9:56 2020/12/28
 * @since 1.2.0
 */

public interface IUserService extends UserDetailsService {

    public User selectUserByName(String name);
}
