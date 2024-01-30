package com.bongsamaru.admin.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.admin.service.UserService;
import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.DonationLedgerVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.UserVO;
import com.bongsamaru.common.VolunteerVO;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
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
	
	@GetMapping("NoticeInsert")
	public String insertNotice() {
		return "admin/insertNotice";
	}

	@PostMapping("NoticeInsert")
	public String insertNoticePro(BoardVO boardVO) {
		userService.insertNotice(boardVO);
		return "redirect:noticeList";
	}
	
}
