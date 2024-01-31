package com.bongsamaru.dona.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	//기부 상세페이지(임시)
	   @GetMapping("/donaDetail1")
	    public String openDonaDetailPage() {
	        return "donation/donaDetail";
	    } 
	   
	//상세페이지조회	   
	   
	   @GetMapping("/donaDetail")
	   @ResponseBody
	    public String donaDetailPage1(@RequestParam(name="donId") Integer donId, Model model) {
		   DonaVO dona = donaService.getDonaDetail(donId);
		   System.out.println(dona);
	        return "donation/donaDetail";
	    } 
	   
//	   @GetMapping("userInfo")
//	   @ResponseBody
//	   public UserVO getUserlOne(@RequestParam(name="memId") String memId,Model model) {
//	      UserVO vo = userService.getUserOne(memId);
//	      return vo;
//	   }
	   
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
