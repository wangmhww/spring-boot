package com.wm.service;

import com.wm.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author wangm
 * @title: IUserService
 * @projectName security
 * @description: TODO
 * @date 2020/12/2621:22
 */
public interface IUserService  extends UserDetailsService {

    public User getUserByName(String userName);

}
