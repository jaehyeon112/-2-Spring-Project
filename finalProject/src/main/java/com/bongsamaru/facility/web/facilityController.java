package com.bongsamaru.facility.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.common.VO.ChallengesVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.facility.Service.FacilityService;
@Controller
public class facilityController {
	@Autowired
	FacilityService facilityService;

	
	
	
	@GetMapping("/facilityList")
	public String getFacilityList(String facZip2, String facType, Model model) {
		List<FacilityVO> list = facilityService.getFacilityList(facZip2, facType);
		model.addAttribute("facilityList", list);
		System.out.println(list);
		/*
		 * // 중복을 제거한 Set을 생성 Set<String> uniqueFacTypes = new HashSet<>(); for
		 * (FacilityVO facility : list) { uniqueFacTypes.add(facility.getFacType()); }
		 * List<String> uniqueFacTypesList = new ArrayList<>(uniqueFacTypes);
		 * model.addAttribute("uniqueFacTypes", uniqueFacTypesList);
		 */
		return "facility/facilityList";
	}
	
	
	@GetMapping("/facilityFilter")
	@ResponseBody
	public List<FacilityVO> getFilterList(String facZip2, String facType){
		List<FacilityVO> filter = facilityService.getFacilityList(facZip2, facType);
		System.out.println(facType);
		return filter;
	}
	
	// /list/paging?page=1
	/*
	 * @GetMapping("/paging") public String paging(@PageableDefault(page=1) Pageable
	 * pageable, Model model){ pageable.getPageNumber(); Page<FacilityVO>
	 * facilityList = facilityService.paging(pageable;) return
	 * 
	 * }
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
	
	
	
	//시설 마이페이지
	 //마이페이지 (기부)
	@GetMapping("/facilityMyPage")
	public String getFacilityMyPage(Model model) {
		List<DonaVO> list = facilityService.getDonaList();
		model.addAttribute("donaList", list);
		return "facility/myPageDona";
	}
	 //마이페이지(시설봉사신청, 신청진행상황)
	@GetMapping("/facilityMyPage/volJoin")
	public String getFacilityMyPageVol(Model model,String facId) {
		// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		// String facId = userDetails.getUsername();
		List<VolActVO> list = facilityService.getVolunteerJoinList(facId);
		model.addAttribute("volList", list);
		return "facility/myPageVolJoin";
	}

	//시설봉사등록
	@PostMapping("InvolJoin")
	@ResponseBody
	public int facVolInsert (VolActVO volActVO) {
		volActVO.setFacId("greenFrog");
		
		return facilityService.InsertFacVol(volActVO);
	}
		
	
	//마이페이지
	@GetMapping("facilityMyPage/volFinish")
	public String getFacilityMyPageVolFinish(Model model) {
		
		return "facility/myPageVolFinish";
	}
	
	
	
}
