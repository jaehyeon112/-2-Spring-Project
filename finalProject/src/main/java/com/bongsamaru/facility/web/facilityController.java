package com.bongsamaru.facility.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.facility.Service.FacilityService;
/**
 * 시설소개 관련, crud
 * 시설 마이페이지 - 기부내역조회, 시설봉사 등록 조회 승인...
 * @author 예빈
 * 
 */
@Controller
public class facilityController {
	
	@Autowired
	FacilityService facilityService;

	
	/**
	 * 사이트에 가입한 시설 리스트
	 * @param facZip2
	 * @param facType
	 * @param model
	 * @return
	 */
	@GetMapping("/facilityList")
	public String getFacilityList(String facZip2, String facType, Model model) {
		List<FacilityVO> list = facilityService.getFacilityList(facZip2, facType);
		model.addAttribute("facilityList", list);
		
		return "facility/facilityList";
	}
	
	
	/**
	 * 시설상세정보사이트
	 * @param facId
	 * @param model
	 * @param request
	 * @return
	 */
	 @GetMapping("fInfo/facilityInfo")
		public String getFacilityInfo(@RequestParam(name="facId") String facId, Model model,HttpServletRequest request) {
		 	FacilityVO findVO =facilityService.getFacilityInfo(facId);
			request.getSession().setAttribute("facilityInfo",findVO);
			model.addAttribute("facilityInfo", findVO);
			
			return "facility/facilityInfo";
		}
	 
	/**
	 * 시설이 기부하는 관련 정보
	 * @param facId
	 * @param facilityVO
	 * @param model
	 * @return
	 */
	 
	@GetMapping("fInfo/fundingList")
	public String getFundingList(@RequestParam(name="facId") String facId,  FacilityVO facilityVO, Model model) {
		//기부중리스트
		List<FundingVO> list = facilityService.getFundingList(facId);
		model.addAttribute("fundingList", list);
		//기부마감리스트
		List<FundingVO> listed = facilityService.getFundedList(facId);
		model.addAttribute("fundedList", listed);
		return "facility/facilityDonation";
	}

	/**
	 * 시설이 봉사한 관련 정보 리스트
	 * @param facId
	 * @param model
	 * @return 봉사리스트보는 페이지
	 */
	@GetMapping("fInfo/volunteerList")
	public String getVolunteerList(@RequestParam(name="facId") String facId, Model model) {
		List<VolunteerVO> list = facilityService.getVolunteerList(facId);
		model.addAttribute("volList", list);
		return "facility/facilityVolunteer";
	}
	
	
	
	//시설 마이페이지	
	//마이페이지 (기부)
	@GetMapping("facilityMyPage")
	public String getFacilityMyPage(Model model, String facId) {
		List<DonaVO> list = facilityService.getDonaList(facId);
		model.addAttribute("donaList", list);
		return "facility/myPageDona";
	}
	
	 //마이페이지(시설봉사신청, 신청진행상황)
	@GetMapping("facilityMyPage/volJoin")
	public String getFacilityMyPageVol(Model model,Principal principal) {
		// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		// String facId = userDetails.getUsername();
		List<VolActVO> list = facilityService.getVolunteerJoinList(principal.getName());
		model.addAttribute("volList", list);
		return "facility/myPageVolJoin";
	}

	//시설봉사등록
	@PostMapping("/InvolJoin")
	@ResponseBody
	public int facVolInsert (VolActVO volActVO, Principal principal) {
		volActVO.setFacId(principal.getName());
		System.out.println("값"+volActVO);
		
		return facilityService.InsertFacVol(volActVO);
	}
		
	
	//마이페이지
	@GetMapping("facilityMyPage/volFinish")
	public String getFacilityMyPageVolFinish(Model model) {
		
		return "facility/myPageVolFinish";
	}
	
	
	
}
