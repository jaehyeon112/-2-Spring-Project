package com.bongsamaru.bongsa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolActVO;

@Controller
public class BongsaController {
	@Autowired
	AdminService userService;
	
	@Autowired
	BongsaService bongsaService;
	
	@GetMapping("/daily")
	public String goToDaily() {
		return "bongsa/DailyVol";
	}

	@GetMapping("/Group")
	public String goToGroup(Model model) {
		List<FacilityVO> list = userService.meetingList();
		model.addAttribute("meet", list);
		List<TagVO> tags = userService.tagList();
		model.addAttribute("tag", tags);
		return "bongsa/GroupVol";
	}
	
	@GetMapping("/FacilityVol")
	public String goToFacility(Model model) {
		List<VolActVO> list = bongsaService.facilityList();
		model.addAttribute("fac", list);
		
		return "bongsa/FacilityVol";
	}
	
}
