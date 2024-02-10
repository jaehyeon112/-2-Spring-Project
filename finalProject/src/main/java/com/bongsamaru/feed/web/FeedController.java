package com.bongsamaru.feed.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bongsamaru.feed.service.FeedService;
import com.bongsamaru.feed.service.FeedVO;
import com.bongsamaru.user.service.UserDetailVO;

@Controller
public class FeedController {
	
	@Autowired
	FeedService feedService;
	
	// 내피드
	 @GetMapping("/myfeed")
	 public String myFeed(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	    	 Object principal = auth.getPrincipal();
	            
	         if (principal instanceof UserDetails) {
	                UserDetailVO userDetailVO = (UserDetailVO) principal;

	                System.out.println(userDetailVO.getUserVO() + "확인");
	                
	                model.addAttribute("userVO", userDetailVO.getUserVO());
	         }
	      }

	      return "feed/myFeed"; 
	}
	 @GetMapping("/feed/{memId}")
	 public String feed(@PathVariable String memId, Model model) {
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	         
	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	         Object principal = auth.getPrincipal();
	         
	         if (principal instanceof UserDetails) {
	             UserDetailVO userDetailVO = (UserDetailVO) principal;
	             System.out.println(userDetailVO.getUserVO() + " 확인");
	             
	             model.addAttribute("userVO", userDetailVO.getUserVO());
	         }
	     }
	     
	     // memId를 이용하여 해당 멤버의 피드를 가져오는 로직을 추가하세요.
	     List<FeedVO> list = feedService.getFeedList(memId);
	     model.addAttribute("list", list);
	     
	     return "feed/feed";
	 }
}
