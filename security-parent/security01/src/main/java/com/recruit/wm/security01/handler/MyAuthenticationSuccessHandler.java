package com.recruit.wm.security01.handler;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangmingf
 * @Date Create in 13:39 2020/12/28
 * @since 1.2.0
 */
@AllArgsConstructor
 public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

     private String redirectUrl;

     @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
         System.out.println("认证成功处理器");
         response.sendRedirect(redirectUrl);
    }

}
