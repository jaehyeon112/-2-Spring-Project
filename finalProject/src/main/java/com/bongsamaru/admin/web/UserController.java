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
		System.out.println(list);
		System.out.println(memStat);
		model.addAttribute("userList",list);
		return "admin/userList";
	}
	
	@GetMapping("userInfo")
	@ResponseBody
	public UserVO getUserlOne(@RequestParam(name="memId") String memId,Model model) {
		UserVO vo = userService.getUserOne(memId);
		return vo;
	}
	
	@GetMapping("volCount")
	@ResponseBody
	public VolunteerVO getFacVol(@RequestParam(name="memId") String memId,Model model) {
		VolunteerVO vo = userService.volCount(memId);
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
