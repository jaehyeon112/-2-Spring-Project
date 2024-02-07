package com.bongsamaru.user.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class CommonUserController {
	
	@Value("${kakao.client.id}")
	private String kakaoClientId;

	@Value("${kakao.redirect.url}")
	private String kakaoRedirectUrl;
	
	@Value("${kakao.client.secret}")
	private String secret_code;
	
	 @GetMapping("/login")
	    public String loginForm(Model model){
		 model.addAttribute("kakaoUrl", "https://kauth.kakao.com/oauth/authorize?client_id=" 
		            + kakaoClientId + "&redirect_uri=" + kakaoRedirectUrl + "&response_type=code");
		 
		 
	        return "login/FacilityLogin";
	    }
	

	
	
}


