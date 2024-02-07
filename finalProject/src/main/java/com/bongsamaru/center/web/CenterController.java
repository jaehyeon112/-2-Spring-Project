package com.bongsamaru.center.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.center.service.CenterService;
import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.PageVO;

@Controller
public class CenterController {
	
	 @Autowired
	 CenterService centerService;

	 // FAQ 페이지
	 @GetMapping("/faq")
	 public String faqList(PageVO vo, Model model
			 	, @RequestParam(value="category", required = false, defaultValue = "a01")String category
				, @RequestParam(value="start", required = false)String start
				, @RequestParam(value="end", required = false)String end) {

	             
	            int total = centerService.getFaqCategoryCount(category);
	            // start와 end가 null일 경우 기본값으로 1과 10을 사용
	            int startPage = (start == null) ? 1 : Integer.parseInt(start);
	            int endPage = (end == null) ? 10 : Integer.parseInt(end);
	            
	            
	            vo = new PageVO(total, startPage, endPage, category);
	            System.out.println(startPage);
	            System.out.println(endPage);
	         	System.out.println(start);
	         	System.out.println(end);
	         	List<FaqVO> list = centerService.getFaqList(vo);
	         	model.addAttribute("vo",vo);
	         	model.addAttribute("category",category);
	         	model.addAttribute("list", list);
	         	
	         	System.out.println(list);
	         	System.out.println(vo);
	         	System.out.println("확인: " + category);
	     return "center/faq"; // 해당 페이지의 뷰 이름을 반환합니다. 필요에 따라 수정해주세요.
	 }
	
	 // 공지사항
	 @GetMapping("notice")
	 public String noticeList(BoardVO vo, Model model) {
		 
		 List<BoardVO> list = centerService.getNoticeList(vo);
		 model.addAttribute("list",list);
		 return "center/notice";
	 }
	 
	 // 1:1문의 내역 
	 @GetMapping("inquiry")
	 public String inquiryList() {
		 
		 return "center/inquiry";
	 }
	 
	 // 문의하기 페이지
	 @GetMapping("receipt")
	 public String receiptPage() {
		 
		 return "center/receipt";
	 }
	 
}
