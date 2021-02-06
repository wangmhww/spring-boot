package com.oauth.wn.oauth2ssoclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso  //单点登录是必须配置
public class Oauth2SsoClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2SsoClientDemoApplication.class, args);
    }

}
