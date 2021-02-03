package com.oauth.wm.jwtdemo.config;

import com.oauth.wm.jwtdemo.filter.JWTAuthenticationEntryPoint;
import com.oauth.wm.jwtdemo.filter.JWTAuthenticationFilter;
import com.oauth.wm.jwtdemo.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author wangm
 * @title: WebSecurityConfig
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/321:39
 */
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Value("${server.ignore.url}")
    private String  ignoreUrl;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] ignoreUrls = ignoreUrl.split(",");

        http.cors()
                .and().authorizeRequests()
                .antMatchers(ignoreUrls).permitAll()
                .antMatchers("/user/**").hasRole("admin")
                .anyRequest().permitAll()
                // 添加Jwt登录拦截器
                .and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
                // 添加JWT鉴权拦截器
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 设置Session的创建策略为：Spring Security不创建HttpSession
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 匿名用户访问无权限资源时的异常处理
                .and().exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .and().csrf().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 注册跨域配置
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
