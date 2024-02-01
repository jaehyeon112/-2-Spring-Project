package com.bongsamaru.dona.web;

import java.util.List;

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
		   String h = "h";
		   System.out.println("=======================");
		   System.out.println(donaService.getCategoryList(h));
		   System.out.println("=======================");
		   model.addAttribute("categoryList", donaService.getCategoryList(h));
	        return "donation/donaMain";
	    }

	// 기부 유형(categories) 전체리스트
	   @GetMapping("/catetory")
	   @ResponseBody
	   public List<DonaVO> showCategories(@RequestParam("h") String h) {
		  //model.addAttribute("categories", donaService.getCategoryList(h));
		  return donaService.getCategoryList(h);		
		   
	   }
	   


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
	
	//폼
	   @GetMapping("/form")
	    public String opeform() {
	        return "donation/form";
	    }   
}
