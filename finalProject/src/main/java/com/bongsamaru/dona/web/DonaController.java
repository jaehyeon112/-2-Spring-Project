package com.bongsamaru.dona.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	    public String openDonaMainPage( Model model) {
		   List<DonaVO> donaList = donaService.getDonaList();
		   model.addAttribute("list", donaList);
		      
		   String h = "h";
		   List<DonaVO> categoryList = donaService.getCategoryList(h);
		   model.addAttribute("categoryList", categoryList);
		   
	        return "donation/donaMain";
	    }

	 // 기부상세 페이지
	   @GetMapping("/donaDetail")
	    public String donaDetailPage2(@RequestParam("id") Integer donId, @RequestParam(name = "facId", required = true) String facId, Model model) { 
		   //상세페이지
		   DonaVO dona = donaService.getDonaDetail(donId, facId);
		    model.addAttribute("dona", dona);
		    
		    //기부자목록
		    List<DonaVO> donaList = donaService.getDonerList(donId);
		    model.addAttribute("list", donaList);
		    
		    //댓글리스트
		    List<DonaVO> commentList = donaService.getCommentList(donId);
		    model.addAttribute("comment", commentList);
		    
		    //랜덤
		    List<DonaVO> random = donaService.getDonaList();
			   model.addAttribute("randomlist", random);

		    return "donation/donaDetail";
	    }    
	   
	  //댓글 삽입1
	   @PostMapping("/donaDetail")
	   @ResponseBody
	   public String insertComment(@RequestBody DonaVO donaVO, Model model) {
		   donaService.insertComment(donaVO);
	       return "redirect:/donaDetail";
	   }
	
	   
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
	    public String openPaymentPage(@RequestParam Integer donId) {
	        return "donation/payment";
	    } 
	   


	//찐 결제하기
	@PostMapping("/paymentProcess")
	@ResponseBody
	public String payProcess(@RequestBody DonaVO donaVO,Model model) {
		
		donaService.paymentProcess(donaVO);
		return "redirect:donation/paymentComplete";
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
		   model.addAttribute("donaVO", new DonaVO()); // 빈개체 추가
	        return "donation/form";
	    }   
	   
	   
	   @PostMapping("/form")
	   public String registerDona(@ModelAttribute DonaVO donaVO) {
		  
			   donaService.insertDonation(donaVO);
			   donaService.insertDonDetail(donaVO);
			   
			   return "redirect:/donation/donaMain";
		   
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
