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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.common.VO.InterestVO;
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

	                System.out.println(userDetailVO.getUserVO().getMemName() + "확인");
	                String memId = userDetailVO.getUserVO().getId();
	                List<FeedVO> list = feedService.getFeedList(memId);
	                List<FeedVO> list2 = feedService.getFeedFirstList(memId);
	                List<InterestVO> list3 = feedService.getInterestList(memId);
	                System.out.println(list2 + " feed확인");
	                model.addAttribute("list", list);
	                model.addAttribute("feedList", list2);
	                model.addAttribute("InterestList", list3);
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
	     List<FeedVO> list2 = feedService.getFeedFirstList(memId);
         List<InterestVO> list3 = feedService.getInterestList(memId);
         System.out.println(list2 + " feed확인");
	     model.addAttribute("list", list);
	     model.addAttribute("feedList", list2);
	     model.addAttribute("InterestList", list3);
	     
	     return "feed/feed";
	 }
	 
	 @PostMapping("/likes")
	 @ResponseBody
	 public void likeInsert(@RequestParam Integer boardId, Model model) {
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	         Object principal = auth.getPrincipal();

	         if (principal instanceof UserDetails) {
	             UserDetailVO userDetailVO = (UserDetailVO) principal;
	             System.out.println(userDetailVO.getUserVO() + " 확인");

	             model.addAttribute("userVO", userDetailVO.getUserVO());
	             String memId = userDetailVO.getUserVO().getId();

	          // feedService.getLike 체크
	             boolean isLiked = feedService.getLike(memId, boardId);
	             System.out.println(isLiked + "이거 트루참");
	             if (!isLiked) {
	                 // 좋아요가 되어있지 않은 경우, 좋아요 추가
	                 feedService.LikeInsert(memId, boardId);
	             } else {
	                 // 좋아요가 이미 되어있는 경우, 좋아요 삭제
	                 feedService.DeleteLike(boardId);
	             }
	             
	         }
	     }
	 }
	 
}
