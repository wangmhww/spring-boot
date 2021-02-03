package com.oauth.wm.jwtdemo.filter;

import com.alibaba.fastjson.JSON;
import com.oauth.wm.jwtdemo.exception.BasicExecption;
import com.oauth.wm.jwtdemo.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author wangm
 * @title: JWTAuthorizationFilter
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/322:27
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtTokenUtil.TOKEN_HANDER);
        if (token == null){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(new BasicExecption("token is not expired","无效的token认证")));
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        try {
            usernamePasswordAuthenticationToken = getAuthentication(token);
        } catch (ExpiredJwtException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(new BasicExecption("token is not expired","token已过期，请重新获取")));
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        super.doFilterInternal(request, response, chain);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        String userName = JwtTokenUtil.getUserName(token);

        String role = JwtTokenUtil.getUserRole(token);
        String[] roles = StringUtils.strip(role,"[]").split(",");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String str: roles ) {
            authorities.add(new SimpleGrantedAuthority(str));
        }
        if(userName != null){
            return new UsernamePasswordAuthenticationToken(userName, null, authorities);
        }
        return null;
    }
}
