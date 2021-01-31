package com.recruit.wm.security02.handler;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangm
 * @title: RecruitSecurityExpressionOperations
 * @projectName security-parent
 * @description: TODO
 * @date 2020/12/3021:52
 */
public interface RecruitSecurityExpressionOperations {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
