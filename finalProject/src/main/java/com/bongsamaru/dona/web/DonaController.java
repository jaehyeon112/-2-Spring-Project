package com.bongsamaru.dona.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.user.service.UserDetailVO;


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
	   
	//결제하기		
		 @GetMapping("/payment2")
		    public String myPage(Model model) {
		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        
		        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
		            Object principal = auth.getPrincipal();
		            
		            if (principal instanceof UserDetails) {
		                UserDetailVO userDetailVO = (UserDetailVO) principal;
		                // 이제 userDetailVO를 사용하여 필요한 정보를 얻을 수 있습니다.
		                // 예를 들어, userDetailVO.getUsername()을 호출하여 사용자 이름을 얻을 수 있습니다.
		                // 또는 userDetailVO에 있는 다른 메서드를 호출하여 추가 정보를 얻을 수 있습니다.
		                
		                // 예시: 사용자 이름을 모델에 추가
		                System.out.println(userDetailVO.getUserVO() + "확인");
		                model.addAttribute("list", userDetailVO.getUserVO());
		                // 필요한 경우, 여기에서 userDetailVO의 다른 정보를 모델에 추가할 수 있습니다.
		            }
		        }

		        return "donation/payment"; 
		    }

	//찐 결제하기
	@PostMapping("/paymentProcess")
	@ResponseBody
	public ResponseEntity<String> payPross(@RequestBody DonaVO donaVO) {
		
		int result = donaService.paymentProcess(donaVO);
		
		if(result != 1) {
			return new ResponseEntity <> ("결제성공! "+ result ,  HttpStatus.OK);
		}else {
			return new ResponseEntity <> ("결제 실패" ,  HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
