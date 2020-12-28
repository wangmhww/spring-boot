package com.recruit.wm.security01.handler;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangmingf
 * @Date Create in 13:41 2020/12/28
 * @since 1.2.0
 */
@AllArgsConstructor
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private String redirectUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("认证失败处理器");
        response.sendRedirect(redirectUrl);
    }
}
