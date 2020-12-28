package com.wm.service.impl;

import com.wm.bean.Permission;
import com.wm.bean.User;
import com.wm.dao.PermissionDao;
import com.wm.dao.UserDao;
import com.wm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangm
 * @title: MyUserDetailService
 * @projectName security
 * @description: TODO
 * @date 2020/12/2522:20
 */
@Service
public class MyUserDetailService implements IUserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = getUserByName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user!=null){
            List<Permission> permissions =
                    permissionDao.selectById(user.getId());
//设置权限
            permissions.forEach(permission -> {
                if (permission!=null &&
                        !StringUtils.isEmpty(permission.getEnName())){
                    GrantedAuthority grantedAuthority = new
                            SimpleGrantedAuthority(permission.getEnName());
                    authorities.add(grantedAuthority);
                }
            });
// 封装成UserDetails的实现类
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),user.getPassword(),authorities);
        }else {
            throw new UsernameNotFoundException("用户名不存在");
        }
//        return null;
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }
}
