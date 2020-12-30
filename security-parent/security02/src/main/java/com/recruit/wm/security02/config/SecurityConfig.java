package com.recruit.wm.security02.config;

import com.recruit.wm.security02.handler.RecruitAccessDeniedHandler;
import com.recruit.wm.security02.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/admin/login")
                .and().authorizeRequests()
                .antMatchers("/login.html").permitAll()
//                .antMatchers("/login/admin").hasAuthority("admin")
                .regexMatchers(".+[.]html").permitAll()
                .anyRequest().authenticated()

        .and().exceptionHandling().accessDeniedHandler(new RecruitAccessDeniedHandler());

        http.csrf().disable();
//        http.userDetailsService(userService);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
