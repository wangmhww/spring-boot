package com.recurit.wm.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author wangm
 * @title: AuthorizationServiceConfig2
 * @projectName security-parent
 * @description: TODO
 * @date 2021/1/3122:42
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServiceConfig2 extends AuthorizationServerConfigurerAdapter {
}
