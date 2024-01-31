package com.bongsamaru.facility.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.common.VO.RegionVO;
import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.facility.VO.FacilityVO;
import com.bongsamaru.facility.VO.FundingVO;
import com.bongsamaru.facility.VO.VolunteerVO;
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
		System.out.println(list);
		return "facility/facilityList";
	}
	
	/*
	 * @GetMapping("facilityList/{region}/{facType}") public String
	 * getFacilityFilter(@RequestParam region, @RequestParam facType, Model model) {
	 * List<FacilityVO> list = facilityService.getFacilityList();
	 * model.addAttribute("facilityList", list); System.out.println(list); return
	 * "facility/facilityList"; }
	 */

	
	
	
	
	 @GetMapping("fInfo/facilityInfo")
		public String getFacilityInfo(@RequestParam(name="facId") String facId, Model model,HttpServletRequest request) {
		 	FacilityVO findVO =facilityService.getFacilityInfo(facId);
			request.getSession().setAttribute("facilityInfo",findVO);
			FacilityVO info = facilityService.getFacilityInfo(facId);
			model.addAttribute("facilityInfo", info);
			
			return "facility/facilityInfo";
		}
	
	@GetMapping("fInfo/fundingList")
	public String getFundingList(@RequestParam(name="facId") String facId,  FacilityVO facilityVO, Model model) {
		List<FundingVO> list = facilityService.getFundingList(facId);
		model.addAttribute("fundingList", list);
		List<FundingVO> listed = facilityService.getFundedList(facId);
		model.addAttribute("fundedList", listed);
		return "facility/facilityDonation";
	}
	@GetMapping("fInfo/volunteerList")
	public String getVolunteerList(@RequestParam(name="facId") String facId, Model model) {
		List<VolunteerVO> list = facilityService.getVolunteerList(facId);
		model.addAttribute("volList", list);
		System.out.println(list);
		return "facility/facilityVolunteer";
	}
	
}
