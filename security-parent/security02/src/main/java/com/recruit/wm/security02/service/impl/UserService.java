package com.recruit.wm.security02.service.impl;

import com.recruit.wm.security02.service.IUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author wangmingf
 * @Date Create in 10:00 2020/12/29
 * @since 1.2.0
 */
@Service
public class UserService implements IUserService  {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String pw = passwordEncoder.encode("111111");
        UserDetails userDetails = null;

        if("wangming".equals(username)){
            userDetails = new User(username,pw, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        }else{
            userDetails = new User(username,pw, AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
        }
        return userDetails;
    }

}
