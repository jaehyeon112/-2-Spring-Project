package com.bongsamaru.dona.web;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FileService;


@Controller
public class DonaController {
	
	@Autowired
	DonaService donaService;
	
	@Autowired
	FileService fileService;
	
	//기부 메인페이지 - 전체리스트
	   @GetMapping("/donaMain")
	    public String openDonaMainPage( Model model) {
		   List<DonaVO> donaList = donaService.getDonaList();
		   List<DonaVO> recruitList = donaService.selectRecruitingItems();
		   List<DonaVO> completedList = donaService.selectCompletedItems();
		  
		   
		   model.addAttribute("list", donaList);
		   model.addAttribute("recruitList", recruitList);
		   model.addAttribute("completedList", completedList);
		   
		   
		   String h = "h";
		   List<DonaVO> categoryList = donaService.getCategoryList(h);
		   model.addAttribute("categoryList", categoryList);
		   
	        return "donation/donaMain";
	    }
	   
	// 카테고리에 따른 리스트 가져오기
	   @GetMapping("/donaMain/category/{category}")
	   public String openDonaMainPageByCategory(@PathVariable String category, Model model) {
	      
		   List<DonaVO> donaListByCategory = donaService.getDonaListByCategory(category);
		   	System.out.println("카테고리출력" + donaListByCategory);
	       model.addAttribute("clist", donaListByCategory);
	       	

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
		    List<DonaVO> random = donaService.selectRecruitingItems();
			   model.addAttribute("randomlist", random);

		    return "donation/donaDetail";
	    }    
	   
	  //댓글 삽입1
	   @PostMapping("/donaDetail")
	   @ResponseBody
	   public String insertComment(@RequestBody DonaVO donaVO, Model model) {
		   donaService.insertComment(donaVO);
	       return "댓글 등록 완료!";
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
	   
	
	//등록폼 이동
	   @GetMapping("/form")
	    public String openRegitform(Model model) {
		   String h = "h";
		   List<DonaVO> categoryList = donaService.getCategoryList(h);
		   model.addAttribute("categoryList", categoryList);
		   model.addAttribute("donaVO", new DonaVO()); // 빈개체 추가
	        return "donation/form";
	    }   
	   
	   
	   //등록폼 INSERT
	   @PostMapping(value = "/regitForm", consumes = "multipart/form-data")
	   @ResponseBody
	   public String registerDona(@ModelAttribute DonaVO donaVO,  @RequestParam("uploadfiles") MultipartFile[] uploadfiles, Model model) throws IOException {
			donaService.insertDonation(donaVO);
			
			
			int codeNo = donaVO.getDonId();
			String code = "p08";
			fileService.uploadFiles(uploadfiles, code, codeNo, donaVO.getFacId());
			  
			return "redirect:my/mapage";
	   }
	   
	   //등록폼 INSERT - 일단은..
	   @PostMapping("/regitForm2")
	   @ResponseBody
	   public String registerDona(@RequestBody DonaVO donaVO,  Model model){
		   System.out.println("등록폼제발좀좀좀좀좀!!!! ");  // 디버깅 메시지 추가
		   donaService.insertDonation(donaVO);
			

			  
			return "redirect:donation/donaMain";
	   }
	
	//후기폼으로 GO
	   @GetMapping("/reviewform")
	    public String openRevform(Model model) {
		   return "donation/reviewform";
	   }

	   
	// 기부신청폼
	   @GetMapping("/applyform")
	    public String openapplyform(Model model) {
		   return "donation/forDonaform";
	   }
	   
		// 기부신청폼22
	   @GetMapping("/applyform2")
	    public String openapplyform2(Model model) {
		   return "donation/applyDona";
	   }   
}
