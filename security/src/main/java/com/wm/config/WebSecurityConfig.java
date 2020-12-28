package com.wm.config;

import com.wm.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wangm
 * @title: WebSecurityConfig
 * @projectName security
 * @description: TODO
 * @date 2020/12/2522:16
 */
//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public MyUserDetailService myUserDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("wangming")
//                .password(passwordEncoder().encode("123456"))
//                .authorities("admin").and()
//                .withUser("hww")
//                .password(passwordEncoder().encode("19950804"))
//                .authorities("admin");
        auth.userDetailsService(myUserDetailService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//
//        return super.userDetailsServiceBean();
//    }
}
