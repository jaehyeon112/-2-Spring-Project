package com.bongsamaru.admin.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.admin.service.UserService;
import com.bongsamaru.admin.service.UserVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;

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
	
	@GetMapping("facilityApprove")
	public String getFacilityList(Model model) {
		List<FacilityVO> list = userService.getFacilityList();
		List<DonationVO> list2 = userService.getDonationList();
		model.addAttribute("facilityList",list);
		model.addAttribute("donationList",list2);
		return "admin/facilityApprove";
	}
	
	@GetMapping("approve")
	public String approveList(Model model) {
		List<FacilityVO> list = userService.getFacilityList();
		model.addAttribute("facilityList",list);
		return "admin/approve";
	}

}
