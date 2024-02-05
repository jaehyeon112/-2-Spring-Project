package com.bongsamaru.center.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.center.service.CenterService;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.PageVO;
import com.bongsamaru.user.service.UserDetailVO;

@Controller
public class CenterController {
	
	 @Autowired
	 CenterService centerService;

	 @GetMapping("/faq")
	 public String faqList(PageVO vo, Model model
			 	, @RequestParam(value="category", required=false)String category
				, @RequestParam(value="nowPage", required=false)String nowPage
				, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
	     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	         Object principal = auth.getPrincipal();

	         if (principal instanceof UserDetails) {
	             UserDetailVO userDetailVO = (UserDetailVO) principal;

	             System.out.println(userDetailVO.getUserVO() + "확인");

	             model.addAttribute("userVO", userDetailVO.getUserVO());
	             
	             
	             int total = centerService.getFaqCount();
	         	if (nowPage == null && cntPerPage == null) {
	         		nowPage = "1";
	         		cntPerPage = "10";
	         	} else if (nowPage == null) {
	         		nowPage = "1";
	         	} else if (cntPerPage == null) { 
	         		cntPerPage = "10";
	         	}
	         	vo = new PageVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage),category);
	         	List<FaqVO> list = centerService.getFaqList(vo);
	         	model.addAttribute("pageVO", vo);
	         	model.addAttribute("list", list);
	         	
	         }
	     }
	     return "center/faq"; // 해당 페이지의 뷰 이름을 반환합니다. 필요에 따라 수정해주세요.
	 }
}
