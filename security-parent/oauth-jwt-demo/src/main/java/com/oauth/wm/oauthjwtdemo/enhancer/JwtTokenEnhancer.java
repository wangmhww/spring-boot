package com.oauth.wm.oauthjwtdemo.enhancer;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * token 增强  用户可以再token中设置与业务相关的字段 例如：userName
 * @author wangm
 * @title: JwtTokenEnhancer
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/522:17
 */
public class JwtTokenEnhancer implements TokenEnhancer {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String userName = user.getUsername();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(map);
        return accessToken;
    }
}
