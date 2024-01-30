package com.bongsamaru.dona.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bongsamaru.dona.mapper.DonaMapper;

@Controller
public class DonaController {
	
	@Autowired
	DonaMapper donaMapper;
	
	//기부 메인페이지 - 전체리스트
	   @GetMapping("/donaMain")
	    public String openDonaMainPage(Model model) {
		   model.addAttribute("list", donaMapper.getDonaList());
	        return "donation/donaMain";
	    }

	//기부 상세페이지
	   @GetMapping("/donaDetail")
	    public String openDonaDetailPage() {
	        return "donation/donaDetail";
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
