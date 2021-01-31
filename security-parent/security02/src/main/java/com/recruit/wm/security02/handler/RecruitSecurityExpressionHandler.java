package com.recruit.wm.security02.handler;

import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author wangm
 * @title: RecruitSecurityExpressionHandler
 * @projectName security-parent
 * @description: TODO
 * @date 2020/12/3021:48
 */
@Component
public class RecruitSecurityExpressionHandler implements RecruitSecurityExpressionOperations {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object object = authentication.getPrincipal();
        if(object instanceof UserDetails){
            UserDetails userDetails = (UserDetails) object;
            String name = request.getParameter("name");

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            return authorities.contains(new SimpleGrantedAuthority(name));
        }
        return false;
    }
}
