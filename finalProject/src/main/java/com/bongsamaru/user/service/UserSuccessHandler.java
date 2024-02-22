package com.bongsamaru.user.service;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService; // UserService 주입

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
		        UserDetailVO user = (UserDetailVO) authentication.getPrincipal();
		        request.getSession().setAttribute("userId", user.getUsername());
		        request.getSession().setAttribute("Role", user.getUserVO().getMemStat());
		        request.getSession().setAttribute("profile", user.getUserVO().getProfile());
		        System.out.println(user.getUserVO().getProfile());
		        response.sendRedirect("/"); // 사용자를 홈 페이지로 리다이렉트
        }
    }
