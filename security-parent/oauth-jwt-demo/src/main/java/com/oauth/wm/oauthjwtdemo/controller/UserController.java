package com.oauth.wm.oauthjwtdemo.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author wangm
 * @title: UserController
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/521:58
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication , HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = null;
        if(header != null){
            token = header.substring(header.indexOf("bearer") + 7);
        } else {
            token = request.getParameter("access_token");
        }

        return Jwts.parser().setSigningKey("123123".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody();

    }
}
