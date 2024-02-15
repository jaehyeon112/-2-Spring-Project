package com.bongsamaru.user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	UserDetailVO user = (UserDetailVO) authentication.getPrincipal();
    	System.out.println(user.getUserVO());
        response.sendRedirect("/"); // 사용자를 홈 페이지로 리다이렉트
    }
}