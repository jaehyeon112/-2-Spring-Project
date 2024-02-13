package com.bongsamaru.dona.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.common.service.MailUtil;
import com.bongsamaru.common.service.MailVO;
import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FileService;


/**
 * '기부' 페이지에서 담당하는 모든 기능(게시글 crud, 결제, 신청폼)
 * @author 박현아
 * @since 2023.12.xx
 * @version 1.0
 * @see
 *
 * 
 */


@Controller
public class DonaController {
	
	@Autowired
	DonaService donaService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	MailUtil mail;
	
	
	/**
	 * 
	 * @param model 전체 기부 리스트(모집중, 모집완료), 카테고리 리스트
	 * @return 기부 메인페이지
	 */
	
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
	   
	   
	   
	   /**
	    * 
	    * @param 기부 관련 category 정보 
	    * @param model 카테고리에 따른 리스트 분류
	    * @return 메인페이지
	    */
	// 카테고리에 따른 리스트 가져오기
	   @GetMapping("/donaMain/category/{category}")
	   public String openDonaMainPageByCategory(@PathVariable String category, Model model) {
	      
		   List<DonaVO> donaListByCategory = donaService.getDonaListByCategory(category);
		   	System.out.println("카테고리출력" + donaListByCategory);
	       model.addAttribute("clist", donaListByCategory);
	       	

	       return "donation/donaMain";
	   }

	   
	  /**
	   *  
	   * @param donId 게시글 번호
	   * @param facId 로그인한 시설 아이디
	   * @param model donId 상세게시글, 기부자목록, 댓글리스트, 랜덤게시글 리스트
	   * @return 상세페이지
	   */
	   
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
	   
	   
	   /**
	    * 
	    * @param donaVO 댓글정보가 담긴 VO
	    * @param model 댓글 리스트 정보
	    * @return 상세페이지
	    */
	  //댓글 삽입1
	   @PostMapping("/donaDetail")
	   @ResponseBody
	   public String insertComment(@RequestBody DonaVO donaVO, Model model) {
		   System.out.println(donaVO);
		   donaService.insertComment(donaVO);
	       return "댓글 등록 완료!";
	   }
	
	   
	  /**
	   *  
	   * @param id 게시글 번호
	   * @param model 기부자 목록 리스트
	   * @return 기부자리스트
	   */
	 // 기부상세 - 기부자목록 
	  @GetMapping("/donaDetail/{id}")
	  @ResponseBody
	  public List<DonaVO> donerList(@PathVariable Integer id, Model model) {
		  model.addAttribute("doner", donaService.getDonerList(id));
		  	System.out.println("여기여기여기다!! " + donaService.getDonerList(id));
		  return donaService.getDonerList(id);
	  }
	   
	  
	  //연장 이메일보내기
	  //@Scheduled(cron = "0 0 0 * * *")  // 매일 0시 0분에 실행
	  public void sendExtensionEmails() {
	      List<DonaVO> extensionTargetList = donaService.selectExtensionTargetList();
	      for (DonaVO vo : extensionTargetList) {
	          MailVO mailvo = new MailVO();
	          // 이메일 내용 설정
	          String emailContent = String.format(
	        		    "<div style=\"background-color: lightgray; text-align: center; font-weight: bold; font-size: 17px;\">"
	        		    + "<h1 style=\"padding: 50px;\">행복마루에서 보내드리는 이메일입니다.</h1>"
	        		    + "<p style=\"padding: 50px;\">안녕하세요. %s님,<br>" 
	        		    + "%s 기부 게시글의 마감기한이 1일 남았습니다.<br>"
	        		    + "현재 목표금액 미달로 1회 한정 2주 이내로 기간 연장이 가능합니다.<br>"
	        		    + "연장은 홈페이지의 마이페이지 안에서 가능합니다. 감사합니다.</p>"
	        		    + "</div>", 
	        		    vo.getFacId(), 
	        		    vo.getDonId()
	        		);

	          mailvo.setEmailContent(emailContent);
	          mailvo.setRecipientEmail(vo.getFacEmail());  
	          mail.sendMail(mailvo);
	      }
	  }

	  
	  
	  
	  
	  //모금종료 업데이트
	  @Scheduled(cron = "0 0 0 * * *")  // 매일 0시 0분에 실행
	  public void updateRecStat() {
	      DonaVO donaVO = new DonaVO(); 
	      donaService.updateRecStat(donaVO);
	  }

	  //기한연장하기
	  @PutMapping(value = "/extendDonationPeriod")
	    public String extendDonationPeriod(DonaVO donaVO) {
	        donaService.extendDonationPeriod(donaVO);
	        return "redirect:/";  //마이페이지주소찾기
	    }
	
	  
	  
	   
	 /**
	  *  
	  * @param 결제를 할 기부 상세페이지
	  * @return 결제페이지로 이동
	  */
	//결제창
	   @GetMapping("/payment")
	    public String openPaymentPage(@RequestParam Integer donId) {
	        return "donation/payment";
	    } 
	   

/**
 * 
 * @param donaVO 결제정보 담긴 곳
 * @param model 결제후 결제정보 insert
 * @return 결제완료창으로 이동
 */
	//찐 결제하기
	@PostMapping("/paymentProcess")
	@ResponseBody
	public String payProcess(@RequestBody DonaVO donaVO,Model model) {
		
		donaService.paymentProcess(donaVO);
		return "redirect:donation/paymentComplete";
	}
	
	
	   /**
	    * 
	    * @return 결제완료창으로 이동 
	    */
	//결제완료
	   @GetMapping("/paymentComplete")
	    public String openPaymentCompletePage() {
	        return "donation/paymentComplete";
	    } 
	   

	   /**
	    * 
	    * @param model
	    * @return 기부신청폼으로 이동 
	    */
	// 기부신청폼
	   @GetMapping("/applyform")
	    public String openapplyform(Model model) {
		   return "donation/forDonaform";
	   }
	
	
	   
	/**
	 * 
	 * @param model 카테고리 리스트, 입력값 담을 VO
	 * @return 기부 게시글 등록폼
	 */
	//등록폼 이동
	   @GetMapping("/form")
	    public String openRegitform(Model model) {
		   String h = "h";
		   List<DonaVO> categoryList = donaService.getCategoryList(h);
		   model.addAttribute("categoryList", categoryList);
		   model.addAttribute("donaVO", new DonaVO()); // 빈개체 추가
	        return "donation/form";
	    }   
	   
	   
	   /**
	    * 
	    * @param donaVOㅎ ㅐ당 게시글, 시설회원 아이디 정보 있음
	    * @param uploadfiles 파일업로드
	    * @param model 데이터
	    * @return 마이페이지로 이동
	    * @throws IOException
	    */
	   
	   //등록폼 INSERT
	   @PostMapping(value = "/regitForm", consumes = "multipart/form-data")
	   @ResponseBody
	   public String registerDona(@RequestParam("id") Integer donId, @ModelAttribute DonaVO donaVO,  @RequestParam("uploadfiles") MultipartFile[] uploadfiles, Model model) throws IOException {
			donaService.insertDonation(donaVO);
			
			int codeNo = donaVO.getDonId();
			String code = "p08";
			fileService.uploadFiles(uploadfiles, code, codeNo, donaVO.getFacId());
			  
			return "redirect:my/mapage";
	   }
	   

	/**
	 * 
	 * @param model
	 * @return 후기작성 폼으로 이동
	 */
	//후기폼으로 GO
	   @GetMapping("/reviewform")
	    public String openRevform(@RequestParam Integer donId, @RequestParam String facId,Model model) {
		   
		   model.addAttribute("donId", donId);
		   model.addAttribute("facId", facId);
		   return "donation/reviewform";
	   }
	   

	  /** 
	   *  
	   * @param donaVO donId, facId 정보 담긴 곳
	   * @param uploadfiles 파일업로드
	   * @param model 데이터 담아서
	   * @return 마이페이지로 이동
	   * @throws IOException
	   */
	//후기폼 Insert
	   @PostMapping(value = "/reviewForm", consumes = "multipart/form-data")
	   @ResponseBody
	   public String registerReview(@ModelAttribute DonaVO donaVO,  @RequestParam("uploadfiles") MultipartFile[] uploadfiles, Model model) throws IOException {
			donaService.insertReview(donaVO);
			
			int codeNo = donaVO.getDonId();
			String code = "p08";
			fileService.uploadFiles(uploadfiles, code, codeNo, donaVO.getFacId());
			  
			return "redirect:my/mapage";
	   }
	   
	   
	   
	   
	   
	   // 템플릿 놔둔곳... 입니다.. (삭제)
		// 기부신청폼22
	   @GetMapping("/applyform2")
	    public String openapplyform2(Model model) {
		   return "donation/applyDona";
	   }   
}
