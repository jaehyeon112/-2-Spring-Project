package com.bongsamaru.dona.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;


@Controller
public class DonaController {
	
	@Autowired
	DonaService donaService;
	
	//기부 메인페이지 - 전체리스트
	   @GetMapping("/donaMain")
	    public String openDonaMainPage(Model model) {
		   List<DonaVO> donaList = donaService.getDonaList();
		   model.addAttribute("list", donaList);
		      
		   String h = "h";
		   List<DonaVO> categoryList = donaService.getCategoryList(h);
		   model.addAttribute("categoryList", categoryList);
	        return "donation/donaMain";
	    }

	 // 기부상세 페이지
	   @GetMapping("/donaDetail")
	    public String donaDetailPage2(@RequestParam("id") Integer donId, @RequestParam("facId") String facId, Model model) { 
		   //상세페이지
		   DonaVO dona = donaService.getDonaDetail(donId, facId);
		    model.addAttribute("dona", dona);
		    
		    //기부자목록
		    List<DonaVO> donaList = donaService.getDonaList();
		    model.addAttribute("list", donaList);
		    
		    //댓글리스트
		    List<DonaVO> commentList = donaService.getCommentList(donId);
		    model.addAttribute("comment", commentList);
		    
		    return "donation/donaDetail";
	    }    
	   
	  //댓글 삽입
//	   @PostMapping("/donaDetail/{id}")
//	   public String insertComment(@PathVariable Integer id, @RequestParam("content") String content) {
//		    
//		   DonaVO donaVO = new DonaVO();  // 필요한 데이터에 따라 객체를 생성해야 합니다.
//		    donaService.insertComment(id, donaVO);
//
//		    //return "redirect:/donaDetail?id=" + donId + "&facId=" + donaVO.getFacId();
//		    return "redirect:/donaDetail?id=" + id + "&facId=" + donaVO.getFacId();
//	   }
//	   @PostMapping("/donaDetail/{id}")
//	   public String insertComment(@PathVariable Integer id, @RequestParam("content") String content) {
//	       DonaVO donaVO = new DonaVO();  // DonaVO 객체 생성 및 초기화 (필요한 데이터에 따라서)
//	       donaService.insertComment(id, donaVO);
//	       return "redirect:/donaDetail?id=" + id + "&facId=" + donaVO.getFacId();
//	   }
	   
	 // 기부상세 - 기부자목록 
	  @GetMapping("/donaDetail/{id}")
	  @ResponseBody
	  public List<DonaVO> donerList(@PathVariable Integer id, Model model) {
		  model.addAttribute("doner", donaService.getDonerList(id));
		  	System.out.println("여기여기여기다!! " + donaService.getDonerList(id));
		  return donaService.getDonerList(id);
	  }
	   
	   
	//결제창
	   @GetMapping("/payment")
	    public String openPaymentPage() {
	        return "donation/payment";
	    } 
	   
	//결제완료
	   @GetMapping("/paymentComplete")
	    public String openPaymentCompletePage() {
	        return "donation/paymentComplete";
	    } 
	
	//등록폼
	   @GetMapping("/form")
	    public String openRegitform(Model model) {
		   String h = "h";
		   List<DonaVO> categoryList = donaService.getCategoryList(h);
		   model.addAttribute("categoryList", categoryList);
	 
	        return "donation/form";
	    }   
	
	//후기폼
	   @GetMapping("/reviewform")
	    public String openRevform(Model model) {
		   return "donation/reviewform";
	   }

	// 기부신청폼
	   @GetMapping("/applyform")
	    public String openapplyform(Model model) {
		   return "donation/forDonaform";
	   }
	   
}
