package com.oauth.wm.jwtdemo.filter;

import com.alibaba.fastjson.JSON;
import com.oauth.wm.jwtdemo.utils.JwtTokenUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author wangm
 * @title: JWTAuthenticationFilter
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/322:10
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getParameter("userName"),request.getParameter("password")
        );
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 创建token
        String token = JwtTokenUtil.createToken(user.getUsername(),authorities.toString());
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json; charset=utf-8");
        response.setHeader("token", token);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString("登录成功"));
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String returnData="";
        if (failed instanceof AccountExpiredException) {
            returnData="账号过期";
        }else if (failed instanceof BadCredentialsException) {
            returnData="密码错误";
        }else if (failed instanceof CredentialsExpiredException) {
            returnData="密码过期";
        }else if (failed instanceof DisabledException) {
            returnData="账号不可用";
        }else if (failed instanceof LockedException) {
            returnData="账号锁定";
        }else if (failed instanceof InternalAuthenticationServiceException) {
            returnData="用户不存在";
        }else{
            returnData="未知异常";
        }

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(returnData));
    }
}
