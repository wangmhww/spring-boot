package com.wm.handler;

import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangm
 * @title: MyAuthenticationFailureHandler
 * @projectName security
 * @description: TODO
 * @date 2020/12/280:08
 */
@AllArgsConstructor
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String redirectUrl;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("自定义失败处理器");
        response.sendRedirect(redirectUrl);
    }
}
