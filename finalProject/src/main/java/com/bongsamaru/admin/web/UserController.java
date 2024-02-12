package com.bongsamaru.admin.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.common.VO.AlertVO;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.CodeVO;
import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.DonationLedgerVO;
import com.bongsamaru.common.VO.DonationVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.RemittanceVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.mypage.service.DonledgerVO;

@Component
@Controller
public class UserController {
	@Autowired
	AdminService userService;
	
	@Autowired
	DonaService donaService;
	
	@Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${spring.mail.password}")
    private String senderPassword;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private String mailPort;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String mailSmtpStarttlsRequired;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String mailSmtpSocketFactoryClass;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.fallback}")
    private String mailSmtpSocketFactoryFallback;
    
    
    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private String mailSmtpTimeout;
	
	//@Scheduled(cron = "0 0 0 * * *")
	public void sendMailing() {
		List<RemittanceVO> remList = userService.remittanceList();
		System.out.println();
		for(RemittanceVO vo : remList) {
			if(vo.getChecking()==null) {
				try {
		    		// 이메일 설정 및 인증
		    		Properties props = new Properties();
		    		props.put("mail.smtp.auth", mailSmtpAuth);
		    		props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
		    		props.put("mail.smtp.host", mailHost);
		    		props.put("mail.smtp.port", mailPort);
		    		props.put("mail.smtp.timeout", mailSmtpTimeout);
		    		props.put("mail.smtp.socketFactory.class", mailSmtpSocketFactoryClass);
		    		props.put("mail.smtp.socketFactory.fallback", mailSmtpSocketFactoryFallback);
		    		
		    		Session session = Session.getInstance(props, new Authenticator() {
		    			protected PasswordAuthentication getPasswordAuthentication() {
		    				return new PasswordAuthentication(senderEmail, senderPassword);
		    			}
		    		});
		    		// 이메일 작성
		    		Message message = new MimeMessage(session);
		    		message.setFrom(new InternetAddress(senderEmail));
		    		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(vo.getEmail()));
		    		message.setSubject("행복마루에서 보내드리는 이메일입니다.");
		    		
		    		// 이메일 내용
		    		String emailContent = "<div style=\"background-color: lightgray; text-align: center; font-weight: bold; font-size: 17px;\">"
		    				+ "<h1 style=\"padding: 50px;\">행복마루에서 보내드리는 이메일입니다.</h1>"
		    				+ "<p style=\"padding: 50px;\">안녕하세요. 행복마루입니다.<br>" + "기부금 사용에 대한 영수증 제출 기한이 지났습니다.<br>"
		    				+ "빠른 시일 내에 영수증 첨부 부탁드립니다. 감사합니다.</p>"
		    				+ "</div>";
		    		
		    		message.setContent(emailContent, "text/html; charset=utf-8");
		    		
		    		// 이메일 전송
		    		Transport.send(message);
		    		System.out.println("메일 성공");
		    		
		    	} catch (Exception e) {
		    		System.err.print("이메일 전송 중 에러 발생: {}");
		    	}
			}
		}
	}
	
	@GetMapping("AdminMain")
	public String AdminMain(Model model,@RequestParam(value="volId", required = false)Integer volId) {
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("dona", donaList);
		
		List<DonaVO> before = new ArrayList<>();
		
		for(DonaVO vo : donaList) {
			if(vo.getRecStat()==0) {
				before.add(vo);
			}
		}
		model.addAttribute("before", before);
		System.out.println(before);
		List<DonledgerVO> king = userService.DonationKing();
		model.addAttribute("king", king);
		List<VolunteerVO> list = userService.meetingList(volId);
		model.addAttribute("meet", list);
		List<TagVO> tags = userService.tagList();
		model.addAttribute("tag", tags);
		List<VolunteerVO> facVol = userService.facVolunteerList();
		model.addAttribute("facVol", facVol);
//		List<AlertVO> alert = userService.alertList();
//		model.addAttribute("alert", alert);
		return "admin/adminMain";
	}
	
	@GetMapping("adminHeader")
	@ResponseBody
	public List<AlertVO> alert(Model model) {
		return userService.alertList();
	}

	@GetMapping("moreInfo")
	public String moreInfo() {
		return "admin/moreInfo";
	}

	@GetMapping("donationList")
	public String donationList(Model model) {
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("dona", donaList);
		return "admin/donationList";
	}
	
	@GetMapping("donationMain")
	public String donationMain(@RequestParam(name="recStat") String recStat,Model model) {
		List<DonaVO> ledger = userService.donationLedgerList(recStat);
		model.addAttribute("dona", ledger);
		model.addAttribute("recStat",recStat);
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("facDona", donaList);
		return "admin/donationMain";
	}
	
	@GetMapping("facDonationLedgerList")
	public String facDonationLedgerList(@RequestParam(name="donId") Integer donId,Model model) {
		List<DonaVO> list = userService.facDonLedgerList(donId);
		model.addAttribute("fac", list);
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("facDona", donaList);
		return "admin/facDonationLedgerList";
	}
	
	//기부금 정산
	@GetMapping("donationSettlementList")
	public String donationSettlementList(Model model) {
		List<DonaVO> list = userService.donationSettlement();
		model.addAttribute("donSel", list);
		return "admin/donationSettlementList";
	}
	
	@PostMapping("insertRemittance")
	@ResponseBody
	public int insertRemittance(RemittanceVO remittanceVO) {
		return userService.insertRemittance(remittanceVO);
	}
	
	
	@GetMapping("donationDone")
	public String donationDone(Model model) {
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("dona", donaList);
		return "admin/donationDone";
	}
	
	@GetMapping("volunteerList")
	public String volunteerList(Model model,@RequestParam(value="volId", required = false)Integer volId) {
		List<VolunteerVO> list = userService.meetingList(volId);
		model.addAttribute("meet", list);
		List<TagVO> tags = userService.tagList();
		model.addAttribute("tag", tags);
		return "admin/volunteerList";
	}
	
	@GetMapping("facVolList")
	public String facVolList(Model model) {
		List<VolunteerVO> facVol = userService.facVolunteerList();
		model.addAttribute("facVol", facVol);
		return "admin/facVolList";
	}
	
	@GetMapping("donationReceiptList")
	public String donationReceiptList(Model model) {
		List<RemittanceVO> remList = userService.remittanceList();
		model.addAttribute("remList", remList);
		return "admin/donationReceiptList";
	}
	
	@GetMapping("userList")
	public String getUserlList(@RequestParam(name="memStat") String memStat,Model model) {
		List<UserVO> list = userService.getUserList(memStat);
		model.addAttribute("userList",list);
		return "admin/userList";
	}
	
	@GetMapping("userInfo")
	@ResponseBody
	public UserVO getUserlOne(@RequestParam(name="memId") String memId,Model model) {
		UserVO vo = userService.getUserOne(memId);
		return vo;
	}
	
	@GetMapping("userMeet")
	@ResponseBody
	public List<VolunteerVO> userMeet(@RequestParam(name="memId") String memId,Model model) {
		List<VolunteerVO> list = userService.memMeetList(memId);
		return list;
	}
	
	@GetMapping("facInfo")
	@ResponseBody
	public FacilityVO getFacilityInfo(@RequestParam(name="facId") String facId,Model model) {
		FacilityVO vo = userService.getFacilityInfo(facId);
		return vo;
	}
	
	@GetMapping("donInfo")
	@ResponseBody
	public DonationVO getDonInfo(@RequestParam(name="donId") String donId,Model model) {
		DonationVO vo = userService.getDonOne(donId);
		return vo;
	}
	
	@GetMapping("volCount")
	@ResponseBody
	public VolunteerVO getFacVol(@RequestParam(name="memId") String memId,@RequestParam(name="mId") String mId) {
		VolunteerVO vo = userService.volCount(memId,mId);
		return vo;
	}
	
	@GetMapping("donCount")
	@ResponseBody
	public DonationLedgerVO getDonCount(@RequestParam(name="memId") String memId) {
		DonationLedgerVO vo = userService.donCount(memId);
		return vo;
	}

	@GetMapping("facilityApprove")
	public String getFacilityList(@RequestParam(name="donRegApp") String donRegApp,Model model) {
		List<FacilityVO> list = userService.getFacilityList();
		List<DonationVO> list2 = userService.getDonationList(donRegApp);
		model.addAttribute("facilityList",list);
		model.addAttribute("donationList",list2);
		return "admin/facilityApprove";
	}
	
	@GetMapping("userListDetail")
	public String FacilityList(Model model) {
		List<FacilityVO> list = userService.getFacilityList();
		model.addAttribute("userListDetail",list);
		return "admin/userListDetail";
	}
	
	@GetMapping("approve")
	public String approveList(Model model) {
		List<FacilityVO> list = userService.getFacilityList();
		model.addAttribute("facilityList",list);
		return "admin/approve";
	}
	
	@GetMapping("boardList")
	public String getBoardList(PageVO vo, Model model
						 	, @RequestParam(value="searchKeyword", required = false)String searchKeyword
						 	, @RequestParam(value="category", required = false)String category
							, @RequestParam(value="start", required = false)String start
							, @RequestParam(value="end", required = false)String end) {
		int total = userService.getBoardCnt(vo);
		int startPage = (start == null) ? 1 : Integer.parseInt(start);
        int endPage = (end == null) ? 10 : Integer.parseInt(end);
        
        if(searchKeyword == null) {
        	vo = new PageVO(total, startPage, endPage, category);	            	
        }else {
        	vo = new PageVO(total, startPage, endPage, category,searchKeyword);
        }
		List<BoardVO> list = userService.getBoardList(vo);
		model.addAttribute("boardList",list);
		model.addAttribute("searchKeyword",searchKeyword);
     	model.addAttribute("vo",vo);
     	model.addAttribute("category",category);
     	
		return "admin/boardList";
	}
	
	@GetMapping("inquireList")
	public String getinquireList(PageVO vo,@RequestParam(name="category") String category,Model model) {
		List<BoardVO> list = userService.getBoardList(vo);
		model.addAttribute("inquireList",list);
		return "admin/inquireList";
	}
	
	@GetMapping("faqList")
	public String getFaqList(PageVO vo, Model model
						 	, @RequestParam(value="category", required = false) String category
						 	, @RequestParam(value="mainCodeId", required = false, defaultValue = "a") String mainCodeId
							, @RequestParam(value="start", required = false) String start
							, @RequestParam(value="end", required = false) String end) {
		
		int total = userService.getFaqCnt(vo);
		int startPage = (start == null) ? 1 : Integer.parseInt(start);
        int endPage = (end == null) ? 10 : Integer.parseInt(end);
        
        vo = new PageVO(total, startPage, endPage, category);	            	
     	
		List<FaqVO> list = userService.getFaqList(vo);
		model.addAttribute("vo",vo);
		model.addAttribute("faqList",list);
		model.addAttribute("category",category);
		List<CodeVO> code = userService.subCodeList(mainCodeId);
		model.addAttribute("code",code);
		System.out.println("전체크기"+total);
		return "admin/faqList";
	}
	
	@GetMapping("getReportList")
	public String getReportList(@RequestParam(name="category") String category,Model model) {
		List<ReportVO> list = userService.getReportList(category);
		model.addAttribute("reportList",list);
		return "admin/reportList";
	}
	
	@GetMapping("MoreReport")
	public String getReportList1(@RequestParam(name="category") String category,Model model) {
		List<ReportVO> list = userService.getReportList(category);
		model.addAttribute("reportList",list);
		return "admin/MoreReport";
	}
	
	@GetMapping("noticeInsert")
	public String insertNotice() {
		return "admin/Noticeinsert";
	}

	@PostMapping("noticeInsert")
	public String insertNoticePro(BoardVO boardVO) {
		userService.insertNotice(boardVO);
		return "redirect:boardList?category=b01";
	}
	
	@GetMapping("maxNotice")
	@ResponseBody
	public int maxNotice() {
		var cnt = userService.maxNotice();
		return cnt;
	}
	
	@GetMapping("checkFacDonation")
	@ResponseBody
	public DonaVO checkFacDonation(@RequestParam(name="donId") Integer donId) {
		DonaVO vo = userService.checkFacDonation(donId);
		return vo;
	}
	
	//자주하는 질문 등록
	@GetMapping("faqInsert")
	public String faqInsert() {
		return "admin/faqInsert";
	}
	
	@PostMapping("faqInsert")
	public String faqInsertPro(FaqVO faqVO) {
		userService.faqInsert(faqVO);
		return "redirect:boardList?category=b01";
	}
	
	@PostMapping("inquireComments")
	public String inquireComments(CommentsVO commentsVO) {
		userService.inquireComments(commentsVO);
		return "redirect:inquireList?category=b02";
	}

	@GetMapping("updateFacApp")
	@ResponseBody
	public int updateFacApp(@RequestParam(name="facName") String facName) {
		return userService.updateFacApp(facName);
	}
	
	@GetMapping("updateDonApp")
	@ResponseBody
	public int updateDonApp(@RequestParam(name="facId") String facId) {
		return userService.updateDonApp(facId);
	}
	
	@GetMapping("updateDonRegApp")
	@ResponseBody
	public int updateDonRegApp(@RequestParam(name="donId") String donId) {
		return userService.updateDonRegApp(donId);
	}
	
	@GetMapping("noticeInfo")
	public String getNoticeOne(@RequestParam(name="category") String category,@RequestParam(name="detailCate") Integer detailCate,@RequestParam(name="codeNo") String codeNo,Model model) {
		BoardVO vo = userService.getNoticeOne(category,detailCate);
		List<FilesVO> files = userService.selectFile(codeNo);
		model.addAttribute("files",files);
		System.out.println(files);
		model.addAttribute("info",vo);
		return "admin/noticeInfo";
	}
	
	@GetMapping("inquireInfo")
	public String getNoticeOne1(@RequestParam(name="category") String category,@RequestParam(name="detailCate") Integer detailCate,Model model) {
		BoardVO vo = userService.getNoticeOne(category,detailCate);
		model.addAttribute("info",vo);
		return "admin/inquireInfo";
	}
	
	@GetMapping("getFaqOne")
	public String getFaqOne(@RequestParam(name="faqId") Integer faqId,Model model) {
		FaqVO vo = userService.getFaqOne(faqId);
		model.addAttribute("info",vo);
		return "admin/faqInfo";
	}
	
	@GetMapping("delNotice")
	@ResponseBody
	public int delNotice(@RequestParam(name="category") String category,@RequestParam(name="detailCate") String detailCate) {
		return userService.delNotice(category,detailCate);
	}
	
	@GetMapping("delFaq")
	@ResponseBody
	public int delFaq(@RequestParam(name="faqId") Integer faqId) {
		return userService.delFaq(faqId);
	}
	
	@PostMapping("updateNotice")
	public String updateNotice(BoardVO boardVO) {
		userService.updateNotice(boardVO);
		return "redirect:boardList?category=b01";
	}
	
	@GetMapping("updateInquire")
	@ResponseBody
	public int updateInquire(@RequestParam(name="boardId") Integer boardId) {
		return userService.updateInquire(boardId);
	}
	
	@GetMapping("inquireComment")
	public String inquireComment(@RequestParam(name="category") String category,@RequestParam(name="detailCate") Integer detailCate,Model model) {
		BoardVO vo = userService.getNoticeOne(category,detailCate);
		CommentsVO comm = userService.inquireCommentOne(detailCate);
		model.addAttribute("info",vo);
		model.addAttribute("comm",comm);
		return "admin/inquireComment";
	}

	@PostMapping("updateReport")
	@ResponseBody
	public String updateReport(@RequestParam Integer reqCode, @RequestParam Integer repId) {
		userService.updateReport(reqCode,repId);
		return "redirect:getReportList?category=r01";
	}
	
	@GetMapping("delFile")
	@ResponseBody
	public int delFile(@RequestParam(name="filePath") String filePath) {
		return userService.delFile(filePath);
	}
	
}