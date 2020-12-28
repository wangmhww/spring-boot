package com.recruit.wm.security01.config;

import com.recruit.wm.security01.handler.MyAuthenticationFailureHandler;
import com.recruit.wm.security01.handler.MyAuthenticationSuccessHandler;
import com.recruit.wm.security01.handler.MySessionInformationExpiredStrategy;
import com.recruit.wm.security01.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author wangmingf
 * @Date Create in 10:11 2020/12/28
 * @since 1.2.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${server.ignore.url}")
    private String  ignoreUrl;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] ignoreUrls = ignoreUrl.split(",");

        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/admin/login")
//                .defaultSuccessUrl("/admin/index")
//                .successForwardUrl("/main") // 设置认证成功跳转的url
                // 设置认证成功处理器
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))

//                .failureForwardUrl("/toerror") //设置认证失败跳转的url
                // 设置认证失败处理器
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"))


                .and().authorizeRequests()

                .antMatchers(ignoreUrls).permitAll()
                .anyRequest().authenticated()
                // 设置会话管理
                .and().sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1) //设置最大会话量
                .expiredSessionStrategy(new MySessionInformationExpiredStrategy())
                // 组织第二次登陆
                .maxSessionsPreventsLogin(true)

                //记住我
//                .and().and().rememberMe()
//                .tokenRepository(persistentTokenRepository())//设置持久化仓库
//                .tokenValiditySeconds(3600)//超时时间,单位s默认两周
//                .userDetailsService(userService) //设置自定义登录逻辑

                .and().and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                // 禁用跨站点请求伪造调用
                .and().csrf()
                .disable();
    }

    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
