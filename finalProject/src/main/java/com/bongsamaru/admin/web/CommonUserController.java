package com.bongsamaru.admin.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bongsamaru.admin.service.UserDetailVO;

@Controller
public class CommonUserController {

	@GetMapping("/")
    public String someMethod(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userNickname = null;
        
        if (authentication != null && authentication.isAuthenticated() &&
            authentication.getPrincipal() instanceof UserDetailVO) {
            UserDetailVO userDetails = (UserDetailVO) authentication.getPrincipal();
            userNickname = userDetails.getUserNickname(); // 닉네임 가져오기
        }
        
        model.addAttribute("userNickname", userNickname); // 모델에 닉네임 추가
        return "layout"; // 뷰 이름 반환
    }
	
	
}
