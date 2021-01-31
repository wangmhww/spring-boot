package com.recurit.wm.oauth2.service.impl;

import com.recurit.wm.oauth2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author wangm
 * @title: UserService
 * @projectName security-parent
 * @description: TODO
 * @date 2021/1/119:52
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String passwordstr = passwordEncoder.encode("111111");
        UserDetails userDetails = new User("wangming",passwordstr, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return userDetails;
    }
}
