package com.bongsamaru.admin.web;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.common.service.MailUtil;
import com.bongsamaru.common.service.MailVO;
import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.mypage.service.DonledgerVO;


/**
 * 관리자페이지
 * @author 서영희
 *
 */
@Component
@Controller
@lombok.extern.log4j.Log4j2
public class UserController {
	@Autowired
	AdminService userService;
	
	@Autowired
	DonaService donaService;
	
	@Autowired
	MailUtil mail;
	
	//@Scheduled(cron = "0 0 0 * * *")
	public void sendMailing() {
		List<RemittanceVO> remList = userService.remittanceList();
		for(RemittanceVO vo : remList) {
			if(vo.getChecking()==null) {
				MailVO mailvo = new MailVO();
				// 이메일 내용
	    		String emailContent = "<div style=\"background-color: lightgray; text-align: center; font-weight: bold; font-size: 17px;\">"
	    				+ "<h1 style=\"padding: 50px;\">행복마루에서 보내드리는 이메일입니다.</h1>"
	    				+ "<p style=\"padding: 50px;\">안녕하세요. 행복마루입니다.<br>" + "기부금 사용에 대한 영수증 제출 기한이 지났습니다.<br>"
	    				+ "빠른 시일 내에 영수증 첨부 부탁드립니다. 감사합니다.</p>"
	    				+ "</div>";
	    		mailvo.setEmailContent(emailContent);
	    		mailvo.setRecipientEmail(vo.getEmail());
	    		mail.sendMail(mailvo);
			}
		}
	}
	
	/**
	 * 관리자 메인페이지
	 * @return
	 */
	@GetMapping("AdminMain")
	public String AdminMain(Model model,VolunteerVO volunteerVO) {
		//기부 전체 리스트
		List<DonaVO> donaList = donaService.getDonaList();
		List<DonaVO> before = new ArrayList<>();
		
		for(DonaVO vo : donaList) {
			if(vo.getRecStat().equals('0')) {
				before.add(vo);
			}
		}
		//진행중인 기부 리스트
		model.addAttribute("before", before);
		
		//상위 기부랭킹 3개
		List<DonledgerVO> king = userService.DonationKing();
		model.addAttribute("king", king);
		
		//진행중인 모임 리스트
		volunteerVO.setRoomStat(1);
		List<VolunteerVO> list = userService.meetingList(volunteerVO);
		model.addAttribute("meet", list);		
		//모임의 태그
		List<TagVO> tags = userService.tagList(null);
		model.addAttribute("tag", tags);
		
		//진행중인 시설봉사 리스트
		List<VolunteerVO> facVol = userService.facVolunteerList();
		model.addAttribute("facVol", facVol);
		
		log.info(facVol);	//sysout 대신에 더 상세하게 어디서 적히는지 알 수 있음
//		List<AlertVO> alert = userService.alertList();
//		model.addAttribute("alert", alert);
		return "admin/adminMain";
	}
	
	/**
	 * 관리자 헤더
	 * @return
	 */
	@GetMapping("adminHeader")
	@ResponseBody
	public List<AlertVO> alert(Model model) {
		return userService.alertList();
	}
	
	
	/**
	 * 회사소개
	 * @return
	 */
	@GetMapping("moreInfo")
	public String moreInfo() {
		return "admin/moreInfo";
	}

	/**
	 * 기부금프로젝트 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("donationList")
	public String donationList(Model model) {
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("dona", donaList);
		return "admin/donationList";
	}
	
	
	/**
	 * 기부금장부 메인(첫페이지)
	 * @param recStat
	 * @param model
	 * @return
	 */
	@GetMapping("donationMain")
	public String donationMain(@RequestParam(defaultValue = "0", required = false) String recStat,Model model) {
		//기부금 장부 리스트
		List<DonaVO> ledger = userService.donationLedgerList(recStat);
		model.addAttribute("dona", ledger);
		model.addAttribute("recStat",recStat);
		
		//기부금 프로젝트 리스트
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("facDona", donaList);
		return "admin/donationMain";
	}
	
	/**
	 * 프로젝트별 기부금 장부 리스트
	 * @param donId
	 * @param model
	 * @return
	 */
	@GetMapping("facDonationLedgerList")
	public String facDonationLedgerList(@RequestParam(name="donId") Integer donId, Model model) {
		//
		List<DonaVO> list = userService.facDonLedgerList(donId);
		model.addAttribute("fac", list);
		
		//
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("facDona", donaList);
		return "admin/facDonationLedgerList";
	}
	
	/**
	 * 기부금 정산 : 기간만료,미정산건에 대한 부분
	 * @param model
	 * @return
	 */
	@GetMapping("donationSettlementList")
	public String donationSettlementList(Model model) {
		List<DonaVO> list = userService.donationSettlement();
		model.addAttribute("donSel", list);
		return "admin/donationSettlementList";
	}
	
	/**
	 * 송금시 기부금 정산 테이블에 입력
	 * @param remittanceVO
	 * @return
	 */
	@PostMapping("insertRemittance")
	@ResponseBody
	public int insertRemittance(RemittanceVO remittanceVO) {
		return userService.insertRemittance(remittanceVO);
	}
	
	/**
	 * 종료된 기부금 프로젝트 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("donationDone")
	public String donationDone(Model model) {
		List<DonaVO> donaList = donaService.getDonaList();
		model.addAttribute("dona", donaList);
		return "admin/donationDone";
	}
	
	/**
	 * 봉사 리스트
	 * @param model
	 * @param volId
	 * @return
	 */
	@GetMapping("volunteerList")
	public String volunteerList(Model model,VolunteerVO vo) {
		
		List<VolunteerVO> list = userService.meetingList(vo);
		model.addAttribute("meet", list);
		
		List<TagVO> tags = userService.tagList(null);
		model.addAttribute("tag", tags);
		
		return "admin/volunteerList";
	}
	
	/**
	 * 시설 봉사 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("facVolList")
	public String facVolList(Model model) {
		List<VolunteerVO> facVol = userService.facVolunteerList();
		model.addAttribute("facVol", facVol);
		return "admin/facVolList";
	}
	
	/**
	 * 기부금 영수증 처리현황 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("donationReceiptList")
	public String donationReceiptList(Model model) {
		List<RemittanceVO> remList = userService.remittanceList();
		model.addAttribute("remList", remList);
		return "admin/donationReceiptList";
	}
	
	/**
	 * 일반회원 리스트
	 * @param memStat
	 * @param model
	 * @return
	 */
	@GetMapping("userList")
	public String getUserlList(@RequestParam(name="memStat") String memStat,Model model) {
		List<UserVO> list = userService.getUserList(memStat);
		model.addAttribute("userList",list);
		return "admin/userList";
	}
	
	/**
	 * 개인회원 정보
	 * @param memId
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/userInfo")
	@ResponseBody
	public UserVO getUserlOne(@RequestParam(name="memId") String memId,Model model) {
		UserVO vo = userService.getUserOne(memId);
		return vo;
	}
	
	/**
	 * 회원 모임가입 리스트
	 * @param memId
	 * @param model
	 * @return
	 */
	@GetMapping("userMeet")
	@ResponseBody
	public List<VolunteerVO> userMeet(@RequestParam(name="memId") String memId,Model model) {
		List<VolunteerVO> list = userService.memMeetList(memId);
		return list;
	}
	
	/**
	 * 시설 정보
	 * @param facId
	 * @param model
	 * @return
	 */
	@GetMapping("facInfo")
	@ResponseBody
	public FacilityVO getFacilityInfo(@RequestParam(name="facId") String facId,Model model) {
		FacilityVO vo = userService.getFacilityInfo(facId);
		return vo;
	}
	
	/**
	 * 기부상세정보
	 * @param donId
	 * @param model
	 * @return
	 */
	@GetMapping("donInfo")
	@ResponseBody
	public DonationVO getDonInfo(@RequestParam(name="donId") String donId,Model model) {
		DonationVO vo = userService.getDonOne(donId);
		return vo;
	}
	
	/**
	 * 기간별 봉사횟수
	 * @param memId
	 * @return
	 */
	@GetMapping("volCount")
	@ResponseBody
	public VolunteerVO getFacVol(@RequestParam(name="memId") String memId) {
		VolunteerVO vo = userService.volCount(memId);
		return vo;
	}
	/**
	 * 기간별 기부금액
	 * @param memId
	 * @return
	 */
	@GetMapping("donCount")
	@ResponseBody
	public DonationLedgerVO getDonCount(@RequestParam(name="memId") String memId) {
		DonationLedgerVO vo = userService.donCount(memId);
		return vo;
	}
	
	/**
	 * 시설가입 승인 신청 리스트
	 * @param donRegApp
	 * @param model
	 * @return
	 */
	@GetMapping("facilityApprove")
	public String getFacilityList(@RequestParam(name="donRegApp") String donRegApp,Model model) {
		List<FacilityVO> facilityList = userService.getFacilityList();
		List<DonationVO> list2 = userService.getDonationList(donRegApp);
		model.addAttribute("facilityList",facilityList);
		model.addAttribute("donationList",list2);
		return "admin/facilityApprove";
	}
	
	/**
	 * 시설 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("userListDetail")
	public String FacilityList(Model model) {
		List<FacilityVO> list = userService.getFacilityList();
		model.addAttribute("userListDetail",list);
		return "admin/userListDetail";
	}
	
	/**
	 * 승인페이지
	 * @param model
	 * @return
	 */
	@GetMapping("approve")
	public String approveList(Model model) {
		List<FacilityVO> list = userService.getFacilityList();
		model.addAttribute("facilityList",list);
		return "admin/approve";
	}
	
	/**
	 * 게시판,페이징
	 * @param vo
	 * @param model
	 * @param searchKeyword
	 * @param category
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("boardList")
	public String getBoardList(PageVO vo, Model model
						 	, @RequestParam(value="searchKeyword", required = false)String searchKeyword
						 	, @RequestParam(value="category", required = false)String category
							, @RequestParam(value="start", required = false,defaultValue = "1")Integer start
							, @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
		int total = userService.getBoardCnt(vo);
        
      
        vo = new PageVO(total, start, end, category,searchKeyword,10);
       
		List<BoardVO> list = userService.getBoardList(vo);
		model.addAttribute("boardList",list);
     	model.addAttribute("vo",vo);
     	
     	//model.addAttribute("searchKeyword",searchKeyword);
     	//model.addAttribute("category",category);
     	
		return "admin/boardList";
	}
	
	/**
	 * 문의사항 리스트
	 * @param vo
	 * @param category
	 * @param model
	 * @return
	 */
	@GetMapping("inquireList")
	public String getinquireList(PageVO vo,@RequestParam(name="category") String category,Model model) {
		List<BoardVO> list = userService.getBoardList(vo);
		model.addAttribute("inquireList",list);
		return "admin/inquireList";
	}
	
	/**
	 * 자주하는질문 리스트
	 * @param vo
	 * @param model
	 * @param category
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("faqList")
	public String getFaqList(PageVO vo, Model model
						 	, @RequestParam(value="category", required = false) String category
							, @RequestParam(value="start", required = false,defaultValue = "1") Integer start
							, @RequestParam(value="end", required = false,defaultValue = "10") Integer end) {
		
		int total = userService.getFaqCnt(vo);
        
        vo = new PageVO(total, start, end, category,10);	            	
     	
		List<FaqVO> list = userService.getFaqList(vo);
		model.addAttribute("vo",vo);
		model.addAttribute("faqList",list);
		model.addAttribute("category",category);
		
		//자주하는 질문 분야 코드
		List<CodeVO> code = userService.subCodeList("a");
		model.addAttribute("code",code);

		return "admin/faqList";
	}
	
	/**
	 * 신고리스트
	 * @param category
	 * @param model
	 * @return
	 */
	@GetMapping("getReportList")
	public String getReportList(@RequestParam String category,Model model) {
		List<ReportVO> list = userService.getReportList(category);
		model.addAttribute("reportList",list);
		return "admin/reportList";
	}
	
	/**
	 * 신고 상세
	 * @param category
	 * @param model
	 * @return
	 */
	@GetMapping("MoreReport")
	public String getReportList1(@RequestParam String category,Model model) {
		List<ReportVO> list = userService.getReportList(category);
		model.addAttribute("reportList",list);
		return "admin/MoreReport";
	}
	
	/**
	 * 공지사항 등록페이지
	 * @return
	 */
	@GetMapping("noticeInsert")
	public String insertNotice() {
		return "admin/Noticeinsert";
	}

	/**
	 * 공지사항 등록 프로세스
	 * @param boardVO
	 * @return 
	 * @return
	 */
	@PostMapping("noticeInsert")
	@ResponseBody
	public BoardVO insertNoticePro(BoardVO boardVO) {
		userService.insertNotice(boardVO);
		System.out.println(boardVO);
		return boardVO;
	}
	
//	/**
//	 * 공지사항 번호
//	 * @return
//	 */
//	@GetMapping("maxNotice")
//	@ResponseBody
//	public int maxNotice() {
//		var cnt = userService.maxNotice();
//		return cnt;
//	}
	
	/**
	 * 기부금에 대한 처리 현황
	 * @param donId
	 * @return
	 */
	@GetMapping("checkFacDonation")
	@ResponseBody
	public DonaVO checkFacDonation(@RequestParam(name="donId") Integer donId) {
		DonaVO vo = userService.checkFacDonation(donId);
		return vo;
	}
	
	/**
	 * 자주하는 질문 등록
	 * @return
	 */
	@GetMapping("faqInsert")
	public String faqInsert() {
		return "admin/faqInsert";
	}
	
	/**
	 * 자주하는 질문 등록
	 * @param faqVO
	 * @return
	 */
	@PostMapping("faqInsert")
	public String faqInsertPro(FaqVO faqVO) {
		userService.faqInsert(faqVO);
		return "redirect:boardList?category=b01";
	}
	
	/**
	 * 문의사항 답변 등록
	 * @param commentsVO
	 * @return
	 */
	@PostMapping("inquireComments")
	public String inquireComments(CommentsVO commentsVO) {
		userService.inquireComments(commentsVO);
		return "redirect:inquireList?category=b02";
	}

	/**
	 * 시설가입 승인 처리
	 * @param facName
	 * @return
	 */
	@GetMapping("updateFacApp")
	@ResponseBody
	public int updateFacApp(@RequestParam(name="facName") String facName) {
		return userService.updateFacApp(facName);
	}
	
	/**
	 * 시설 기부금 신청 승인처리
	 * @param facId
	 * @return
	 */
	@GetMapping("updateDonApp")
	@ResponseBody
	public int updateDonApp(@RequestParam(name="facId") String facId) {
		return userService.updateDonApp(facId);
	}
	
	/**
	 * 시설 기부금 프로잭트 등록 승인 처리
	 * @param donId
	 * @return
	 */
	@GetMapping("updateDonRegApp")
	@ResponseBody
	public int updateDonRegApp(@RequestParam(name="donId") String donId) {
		return userService.updateDonRegApp(donId);
	}
	
	/**
	 * 공지사항 상세
	 * @param category
	 * @param detailCate
	 * @param codeNo
	 * @param model
	 * @return
	 */
	@GetMapping("noticeInfo")
	public String getNoticeOne(@RequestParam(name="category") String category,@RequestParam(name="detailCate") Integer detailCate,@RequestParam(name="codeNo") String codeNo,Model model) {
		BoardVO vo = userService.getNoticeOne(category,detailCate);
		List<FilesVO> files = userService.selectFile(codeNo);
		model.addAttribute("files",files);
		System.out.println(files);
		model.addAttribute("info",vo);
		return "admin/noticeInfo";
	}
	
	/**
	 * 문의사항 상세
	 * @param category
	 * @param detailCate
	 * @param model
	 * @return
	 */
	@GetMapping("inquireInfo")
	public String getNoticeOne1(@RequestParam(name="category") String category,@RequestParam(name="detailCate") Integer detailCate,Model model) {
		BoardVO vo = userService.getNoticeOne(category,detailCate);
		model.addAttribute("info",vo);
		return "admin/inquireInfo";
	}
	
	/**
	 * 자주하는 질문 상세
	 * @param faqId
	 * @param model
	 * @return
	 */
	@GetMapping("getFaqOne")
	public String getFaqOne(@RequestParam(name="faqId") Integer faqId,Model model) {
		FaqVO vo = userService.getFaqOne(faqId);
		model.addAttribute("info",vo);
		return "admin/faqInfo";
	}
	
	/**
	 * 문의사항 삭제
	 * @param category
	 * @param detailCate
	 * @return
	 */
	@GetMapping("delNotice")
	@ResponseBody
	public int delNotice(@RequestParam(name="category") String category,@RequestParam(name="detailCate") String detailCate) {
		return userService.delNotice(category,detailCate);
	}
	
	/**
	 * 자주하는 질문 삭제
	 * @param faqId
	 * @return
	 */
	@GetMapping("delFaq")
	@ResponseBody
	public int delFaq(@RequestParam(name="faqId") Integer faqId) {
		return userService.delFaq(faqId);
	}
	
	/**
	 * 공지사항 수정
	 * @param boardVO
	 * @return
	 */
	@PostMapping("updateNotice")
	public String updateNotice(BoardVO boardVO) {
		userService.updateNotice(boardVO);
		return "redirect:boardList?category=b01";
	}
	
	/**
	 * 문의사항 상태 수정
	 * @param boardId
	 * @return
	 */
	@GetMapping("updateInquire")
	@ResponseBody
	public int updateInquire(@RequestParam(name="boardId") Integer boardId) {
		return userService.updateInquire(boardId);
	}
	
	/**
	 * 문의사항 상세
	 * @param category
	 * @param detailCate
	 * @param model
	 * @return
	 */
	@GetMapping("inquireComment")
	public String inquireComment(@RequestParam(name="category") String category,@RequestParam(name="detailCate") Integer detailCate,Model model) {
		BoardVO vo = userService.getNoticeOne(category,detailCate);
		CommentsVO comm = userService.inquireCommentOne(detailCate);
		model.addAttribute("info",vo);
		model.addAttribute("comm",comm);
		return "admin/inquireComment";
	}

	/**
	 * 신고상태 수정
	 * @param reqCode
	 * @param repId
	 * @return
	 */
	@PostMapping("updateReport")
	@ResponseBody
	public String updateReport(@RequestParam Integer reqCode, @RequestParam Integer repId) {
		userService.updateReport(reqCode,repId);
		return "redirect:getReportList?category=r01";
	}
	
	/**
	 * 파일 삭제
	 * @param filePath
	 * @return
	 */
	@GetMapping("delFile")
	@ResponseBody
	public int delFile(@RequestParam(name="filePath") String filePath) {
		return userService.delFile(filePath);
	}
	
}