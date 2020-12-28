package com.wm.config;

import com.wm.handler.MyAuthenticationFailureHandler;
import com.wm.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wangm
 * @title: WebSecurityConfig1
 * @projectName security
 * @description: TODO
 * @date 2020/12/2723:15
 */
@Configuration
public class WebSecurityConfig1 extends WebSecurityConfigurerAdapter {

    @Value("${service.ignore.url}")
    private String url;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] ignoreUrl = url.split(",");

        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login") //登录访问路径，必须和表单提交接口一样
//                .defaultSuccessUrl("/admin/index") // 默认登陆成功访问的路径
//                .successForwardUrl("/main") //认证成功之后转发的路径,必须是Post请求
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))  //自定义认证成功处理器

//                .failureForwardUrl("/toerror") //认证失败之后转发的路径,必须是Post请求
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"))  //自定义登录失败处理器

//                .usernameParameter("userName") //表单提交参数设置
//                .passwordParameter("password11")   //表单提交参数设置

                .and().authorizeRequests()
                // 设置不需要认证的url
                .antMatchers(ignoreUrl).permitAll()
                .anyRequest().authenticated()  // 需要认证

                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth); // 实现该方法需要调用父类的该方法  如果不实现该方法则需要设置一个UserDetailsService
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
