package com.bongsamaru.mypage.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.admin.service.UserDetailVO;
import com.bongsamaru.mypage.service.HeartVO;
import com.bongsamaru.mypage.service.MypageService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MypageController {
	
	 @Autowired
	 MypageService mypageService;
	
	 @GetMapping("/my")
	 public String myPage(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;

	                System.out.println(userDetailVO.getUserVO() + "확인");
	                
	                model.addAttribute("userVO", userDetailVO.getUserVO());

	        		Double heart = mypageService.getHeart(userDetailVO.getUsername());
	        		Integer sumamt = mypageService.getSumAmt(userDetailVO.getUsername());
	        		Integer gibuCount = mypageService.getGibuCount(userDetailVO.getUsername());
	        		model.addAttribute("heart",heart);
	        		model.addAttribute("gibuCount",gibuCount);
	        		model.addAttribute("sumAmt",sumamt);
	         }
	      }

	      return "mypage"; 
	}
	 
}