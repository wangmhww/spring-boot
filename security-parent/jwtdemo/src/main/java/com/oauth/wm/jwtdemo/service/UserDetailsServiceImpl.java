package com.oauth.wm.jwtdemo.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author wangm
 * @title: UserService
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/321:42
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("fox","{noop}123456",
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,ROLE_user"));
    }
}
