package com.recruit.wm.security02.config;

import com.recruit.wm.security02.handler.RecruitAccessDeniedHandler;
import com.recruit.wm.security02.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wangmingf
 * @Date Create in 10:11 2020/12/29
 * @since 1.2.0
 */
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests()
//                .antMatchers("/login.html").permitAll()
//                .antMatchers("/admin/login").hasAuthority("user")

                // 设置用户权限是需要再权限前添加ROLE  例admin 需要在UserService里面返回ROLE_admin权限
//                .antMatchers("/admin/login").hasRole("admin")
//                .antMatchers("/admin/login").hasIpAddress("127.0.0.1")
                //使用access访问权限
//                    .antMatchers("/admin/login").access("hasRole('ADMIN')")
                // 使用自定义权限处理类
//                .antMatchers("/admin/login").access("@recruitSecurityExpressionHandler.hasPermission(request,authentication)")
////                .mvcMatchers("/admin/login").servletPath("/web").permitAll()
//                .regexMatchers(".+[.]jpg").permitAll() // 正则表达式
                .anyRequest().authenticated();


        http.exceptionHandling().accessDeniedHandler(new RecruitAccessDeniedHandler());

        http.csrf().disable();
//        http.userDetailsService(userService);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
