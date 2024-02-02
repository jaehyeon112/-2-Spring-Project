package com.bongsamaru.admin.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.CommentsVO;
import com.bongsamaru.common.DonationLedgerVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.ReportVO;
import com.bongsamaru.common.UserVO;
import com.bongsamaru.common.VolunteerVO;
import com.bongsamaru.file.service.FilesVO;

@Controller
public class UserController {
	@Autowired
	AdminService userService;
	
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
	public VolunteerVO getFacVol(@RequestParam(name="memId") String memId,@RequestParam(name="mId") String mId,Model model) {
		VolunteerVO vo = userService.volCount(memId,mId);
		System.out.println("현재 받아온 것"+vo);
		System.out.println(memId);
		return vo;
	}
	
	@GetMapping("donCount")
	@ResponseBody
	public DonationLedgerVO getDonCount(@RequestParam(name="memId") String memId,Model model) {
		DonationLedgerVO vo = userService.donCount(memId);
		return vo;
	}
	
	@GetMapping("facilityApprove")
	public String getFacilityList(Model model) {
		List<FacilityVO> list = userService.getFacilityList();
		List<DonationVO> list2 = userService.getDonationList();
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
	public String getBoardList(@RequestParam(name="category") String category,Model model) {
		List<BoardVO> list = userService.getBoardList(category);
		model.addAttribute("boardList",list);
		return "admin/boardList";
	}
	
	@GetMapping("inquireList")
	public String getinquireList(@RequestParam(name="category") String category,Model model) {
		List<BoardVO> list = userService.getBoardList(category);
		model.addAttribute("inquireList",list);
		return "admin/inquireList";
	}
	
	@GetMapping("faqList")
	public String getFaqList(Model model) {
		List<FaqVO> list = userService.getFaqList();
		model.addAttribute("faqList",list);
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
		System.out.println(comm);
		return "admin/inquireComment";
	}

	@PostMapping("updateReport")
	@ResponseBody
	public String updateReport(@RequestParam Integer reqCode, @RequestParam Integer repId) {
		userService.updateReport(reqCode,repId);
		return "redirect:getReportList?category=r01";
	}
}