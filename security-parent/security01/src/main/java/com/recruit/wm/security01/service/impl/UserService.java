package com.recruit.wm.security01.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.recruit.wm.security01.bean.Permission;
import com.recruit.wm.security01.bean.User;
import com.recruit.wm.security01.dao.PermissionDao;
import com.recruit.wm.security01.dao.UserDao;
import com.recruit.wm.security01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmingf
 * @Date Create in 9:57 2020/12/28
 * @since 1.2.0
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User selectUserByName(String username) {
        return userDao.selectUserByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = selectUserByName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user != null){
            List<Permission> permissions = permissionDao.selectById(user.getId());
            permissions.forEach(permission -> {
                if(permission != null && !StringUtils.isEmpty(permission.getEnName())){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnName());
                    authorities.add(grantedAuthority);
                }
            });
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),user.getPassword(),authorities);
        }else{
            throw new UsernameNotFoundException("用户名和密码未找到");
        }
    }
}
