package com.bongsamaru.center.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.center.service.CenterService;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.PageVO;

import lombok.extern.log4j.Log4j2;



/**
 * 고객센터(공지사항,자주하는질문,1:1문의내역,1:1문의하기) 페이지
 * @author 나채현
 *
 */
@Log4j2
@Controller
public class CenterController {
	
	 @Autowired
	 CenterService centerService;

	 /**
	  * 자주하는 질문 페이지 페이지네이션 같이 되어있음
	  * @param vo
	  * @param model
	  * @param searchKeyword
	  * @param category
	  * @param start
	  * @param end
	  * @return center/faq
	  */
	 @GetMapping("/faq")
	 public String faqList(PageVO vo, Model model
			 	, @RequestParam(value="searchKeyword", required = false)String searchKeyword
			 	, @RequestParam(value="category", required = false, defaultValue = "a01")String category
				, @RequestParam(value="start", required = false)String start
				, @RequestParam(value="end", required = false)String end) {

	             
	            int total = centerService.getFaqCategoryCount(category);
	            // start와 end가 null일 경우 기본값으로 1과 10을 사용
	            int startPage = (start == null) ? 1 : Integer.parseInt(start);
	            int endPage = (end == null) ? 10 : Integer.parseInt(end);
	            
	            
	            if(searchKeyword == null) {
	            	vo = new PageVO(total, startPage, endPage, category ,10);	            	
	            }else {
	            	vo = new PageVO(total, startPage, endPage, category,searchKeyword,10);
	            }
	            log.info(startPage);
	            log.info(endPage);
	            log.info(start);
	            log.info(end);
	         	List<FaqVO> list = centerService.getFaqList(vo);
	         	model.addAttribute("searchKeyword",searchKeyword);
	         	model.addAttribute("vo",vo);
	         	model.addAttribute("category",category);
	         	model.addAttribute("list", list);
	     return "center/faq"; // 해당 페이지의 뷰 이름을 반환합니다. 필요에 따라 수정해주세요.
	 }
	
	 /**
	  * 공지사항 페이지 페이지네이션 같이되어있음
	  * @param vo
	  * @param model
	  * @param searchKeyword
	  * @param start
	  * @param end
	  * @return center/notice
	  */
	 @GetMapping("notice")
	 public String noticeList(PageVO vo, Model model
			 	, @RequestParam(value="searchKeyword", required = false)String searchKeyword
			 	, @RequestParam(value="start", required = false)String start
				, @RequestParam(value="end", required = false)String end) {
		 
		 int total = centerService.getNoticeCount(vo);
		
		 // start와 end가 null일 경우 기본값으로 1과 10을 사용
         int startPage = (start == null) ? 1 : Integer.parseInt(start);
         int endPage = (end == null) ? 10 : Integer.parseInt(end);
         String category = "b01";        
         
         if(searchKeyword == null) {
         	vo = new PageVO(total, startPage, endPage, category, 10);	            	
         }else {
         	vo = new PageVO(total, startPage, endPage, category,searchKeyword,10);
         }
		 List<BoardVO> list = centerService.getNoticeList(vo);
		 model.addAttribute("searchKeyword",searchKeyword);
		 model.addAttribute("list",list);
		 model.addAttribute("category",category);
		 model.addAttribute("vo",vo);
		 return "center/notice";
	 }
	 
	 /**
	  * 
	  * @return center/inquiry
	  */
	 @GetMapping("inquiry")
	 public String inquiryList() {
		 
		 return "center/inquiry";
	 }
	 
	 /**
	  * 
	  * @return center/receipt
	  */
	 @GetMapping("receipt")
	 public String receiptPage() {
		 
		 return "center/receipt";
	 }
	 
}
