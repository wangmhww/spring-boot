package com.oauth.wm.oauthjwtdemo.config;

import com.oauth.wm.oauthjwtdemo.enhancer.JwtTokenEnhancer;
import com.oauth.wm.oauthjwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangm
 * @title: AuthorizationServiceConfig
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/522:27
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtAccessTokenConverter);
        delegates.add(jwtTokenEnhancer);
        enhancerChain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManagerBean)  // 密码模式需要配置
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter) // 配置token转换器
                .tokenEnhancer(enhancerChain) // 配置tokenEnhance
                .reuseRefreshTokens(false)  // refresh_token是否重复使用
                .userDetailsService(userService) // 刷新令牌授权对应的token检查
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST); // 支持get 和post请求
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        /**
         *授权码模式
         *http://localhost:8080/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://www.baidu.com&scope=all
         *http://localhost:8080/oauth/authorize?response_type=code&client_id=client
         *
         * password模式
         *  http://localhost:8080/oauth/token?username=wangmingf&password=123456&grant_type=password&client_id=client&client_secret=123123&scope=all
         *
         *
         *
         *  刷新令牌
         *  http://localhost:8080/oauth/token?grant_type=refresh_token&client_id=client&client_secret=123123&refresh_token=[refresh_token值]
         */

        clients.inMemory()
                .withClient("client")
                //配置client-secret
                .secret(passwordEncoder.encode("123123"))
                .redirectUris("http://www.baidu.com")
                //配置访问token的有效期
                .accessTokenValiditySeconds(3600)
                // 配置刷新token的有效期
                .refreshTokenValiditySeconds(864000)
                .scopes("all")
                .authorizedGrantTypes("authorization_code","password","refresh_token");
    }
}
