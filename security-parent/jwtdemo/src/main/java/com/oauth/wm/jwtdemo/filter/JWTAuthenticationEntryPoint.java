package com.oauth.wm.jwtdemo.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  匿名用户访问无权限的处理
 * @author wangm
 * @title: JWTAuthenticationEntryPoint
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/322:25
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/javascript;charset=utf-8");
//        response.getWriter().write(JSON.toJSONString("无效的token认证"));
    }
}
