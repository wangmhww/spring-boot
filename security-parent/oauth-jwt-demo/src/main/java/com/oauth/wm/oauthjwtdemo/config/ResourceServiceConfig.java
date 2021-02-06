package com.oauth.wm.oauthjwtdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author wangm
 * @title: ResourceServiceConfig
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/522:11
 */
@Configuration
@EnableResourceServer
public class ResourceServiceConfig  extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and().requestMatchers().antMatchers("/user/**");
    }
}
