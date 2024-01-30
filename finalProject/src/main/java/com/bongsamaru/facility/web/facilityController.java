package com.bongsamaru.facility.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bongsamaru.common.VO.RegionVO;
import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.facility.VO.FacilityVO;
import com.bongsamaru.facility.VO.FundingVO;
@Controller
public class facilityController {
	@Autowired
	FacilityService facilityService;
	
	/*
	 * @ModelAttribute("regions") public List<RegionVO> selectBox() {
	 * 
	 * List<RegionVO> regions = new ArrayList<>(); regions.add(new RegionVO("SEOUL",
	 * "서울특별시")); regions.add(new RegionVO("INCHEON", "인천광역시")); regions.add(new
	 * RegionVO("DEJEON", "대전광역시")); regions.add(new RegionVO("Gwangju", "광주광역시"));
	 * regions.add(new RegionVO("DAEGU", "대구광역시")); regions.add(new
	 * RegionVO("BUSAN", "부산광역시")); regions.add(new RegionVO("ULSAN", "울산광역시"));
	 * regions.add(new RegionVO("gyeonggi-do", "경기도")); regions.add(new
	 * RegionVO("Gangwon-do", "강원도")); regions.add(new RegionVO("chungcheongbugdo",
	 * "충청북도")); regions.add(new RegionVO("chungcheongnamdo", "충청남도"));
	 * regions.add(new RegionVO("jeonlabugdo", "전라북도")); regions.add(new
	 * RegionVO("jeonlanamdo", "전라남도")); regions.add(new RegionVO("gyeongsangbugdo",
	 * "경상북도")); regions.add(new RegionVO("gyeongsangnamdo", "경상남도"));
	 * regions.add(new RegionVO("JEJUDO", "제주도"));
	 * 
	 * return regions; }
	 */
	@GetMapping("facilityList")
	public String getFacilityList(Model model) {
		List<FacilityVO> list = facilityService.getFacilityList();
		model.addAttribute("facilityList", list);
		return "facility/facilityList";
	}
	
	@GetMapping("facilityInfo")
	public String getFacilityInfo(FacilityVO facilityVO, Model model) {
		FacilityVO findVO = facilityService.getFacilityInfo(facilityVO);
		model.addAttribute("facilityInfo",findVO);		
		return "facility/facilityInfo";
	}
	
	@GetMapping("fundingList")
	public String getFundingList(Model model) {
		List<FundingVO> list = facilityService.getFundingList();
		model.addAttribute("fundingList", list);
		return "facility/facilityDonation";
	}
	
	
}
