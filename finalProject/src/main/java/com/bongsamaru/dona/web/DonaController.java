package com.bongsamaru.dona.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		   model.addAttribute("list", donaService.getDonaList());
	        return "donation/donaMain";
	    }

	// 기부 유형(categories) 전체리스트
//	   @GetMapping("/categories")
//	   @ResponseBody
//	   public String showCategories(Model model) {
//		  model.addAttribute("categories", donaService.getCategoryList());
//		  return model;		   
//	   }
	   
	   
	   
	   

	 // 기부상세 페이지
	   @GetMapping("/donaDetail")
	    public String donaDetailPage2(@RequestParam("id") Integer donId, @RequestParam("facId") String facId, Model model) { 
		   DonaVO dona = donaService.getDonaDetail(donId, facId);
		    model.addAttribute("dona", dona);
	       return "donation/donaDetail";
	    }    
	   
	   
	 // 기부상세 - 기부자목록 
	  @GetMapping("/donaDetail/{id}")
	  @ResponseBody
	  public DonaVO donerList(@PathVariable Integer id, Model model) {
		  DonaVO vo = donaService.getDoner(id);
		  model.addAttribute("doner", vo);
		  return vo;
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
	
	//폼
	   @GetMapping("/form")
	    public String opeform() {
	        return "donation/form";
	    }   
}
