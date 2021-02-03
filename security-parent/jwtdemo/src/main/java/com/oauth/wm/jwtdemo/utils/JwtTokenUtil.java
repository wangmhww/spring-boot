package com.oauth.wm.jwtdemo.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangm
 * @title: JwtTokenUtil
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/321:43
 */
public class JwtTokenUtil {
    public static final String TOKEN_HANDER = "token"; // 请求头存放token的字段

    // 前缀
    public static final String TOKEN_PREFIX = "bearer";
    // 签名主题
    public static final String SUBJECT = "app";
    // 过期时间
    public static final long EXPIRITION = 30 * 60 ;
    // 应用密钥
    public static final String APPSECRET_KEY = "123123";
    // 角色权限声明
    public static final String ROLE_CLAIMS = "role";

    /**
     * 创建token
     * @param userName
     * @param role
     * @return
     */
    public static String createToken(String userName, String role){
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);
        String token = Jwts.builder().setSubject(SUBJECT).setClaims(map)
                .claim("userName",userName).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    /**
     * 校验Token
     * @param token
     * @return
     */
    public static Claims checkJwt(String token){
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 从token中获取用户名称
     * @param token
     * @return
     */
    public static String getUserName(String token){
        Claims cliams = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return cliams.get("userName").toString();
    }

    /**
     * 获取用户角色
     * @param token
     * @return
     */
    public static String getUserRole(String token){
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    public static Boolean isExpiration(String token){
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

}
