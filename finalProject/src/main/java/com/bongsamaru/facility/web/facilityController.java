package com.bongsamaru.facility.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.facility.VO.FacilityVO;
@Controller
public class facilityController {
	@Autowired
	FacilityService facilityService;
	
	@ModelAttribute("selectList")
	public List<FacilityVO> getSelectList(Model model) {
		List<FacilityVO> list = facilityService.SelectBoxList();
		model.addAttribute("selectList", list);
		return list;
	}
}
