package com.wm.handler;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangm
 * @title: MyAuthenticationSuccessHandler
 * @projectName security
 * @description: TODO
 * @date 2020/12/280:01
 */
@AllArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String redirectUrl;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("调用登陆成功处理器");
        response.sendRedirect(redirectUrl);
    }
}
