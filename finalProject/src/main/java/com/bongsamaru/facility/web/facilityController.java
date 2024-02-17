package com.bongsamaru.facility.web;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.SubCodeVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.common.mapper.CommonMapper;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.file.service.FileService;

import lombok.extern.log4j.Log4j2;
/**
 * 시설소개 관련, crud
 * 시설 마이페이지 - 기부내역조회, 시설봉사 등록 조회 승인...
 * @author 예빈
 * 
 */
@Log4j2
@Controller
public class facilityController {
	
	@Autowired
	FacilityService facilityService;
	@Autowired
    private FileService fileService;

	@Autowired
	CommonMapper commonMapper;
	@Autowired
	BongsaService bongsaService;
	
	/**
	 * 사이트에 가입한 시설 리스트
	 * @param facZip2
	 * @param facType
	 * @param model
	 * @return
	 */
	@GetMapping("/facilityList")
	public String getFacilityList(String facZip2, 
								  String facType,
								  PageVO vo, 
								  Integer cntPerPage,
								  String facId,
								  Model model,
								  @RequestParam(value="category", required = false)String category,
								  @RequestParam(value="start", required = false,defaultValue = "1")Integer start,
								  @RequestParam(value="end", required = false,defaultValue = "10")Integer end) {
		int total = facilityService.getCategoryCount(facZip2, facType);
		
        // start와 end가 null일 경우 기본값으로 1과 10을 사용
		
        vo = new PageVO(total,start, end, category ,6);
     	model.addAttribute("vo",vo);
     	model.addAttribute("category",category);
     	
     	List<FacilityVO> list = facilityService.getFacilityList(vo,facZip2, facType,facId);
     	model.addAttribute("facilityList", list);
     	List<SubCodeVO> z =commonMapper.subCodeList("z");
     	List<SubCodeVO> f = commonMapper.subCodeList("f");
     	model.addAttribute("subZ", z);
     	model.addAttribute("subF", f);
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
	@GetMapping("/fac")
	public String getFacilityMyPage(Model model,Integer recStat,Principal principal) {
		
		return "facility/myPageDona2";
	}
	
	
	  @GetMapping("/fac/donaInfo")
	   public String getFacilityMyPageDona(Model model,Integer recStat,Principal principal) {
	      
	      List<DonaVO> list1 = facilityService.getDonaList(principal.getName(), 0); // 모금완료
	      model.addAttribute("donaList1", list1);
	      log.info(list1);
	      
	      List<DonaVO> list0 = facilityService.getDonaList(principal.getName(), 1); //모금중
	      model.addAttribute("donaList0", list0);
	      log.info(list0);
	      return "facility/myPageDona";
	   }
	
	 //마이페이지(시설봉사신청, 신청진행상황)
	  @GetMapping("/fac/volJoin")
	  public String getFacilityMyPageVol(Model model,
	                                     Principal principal,
	                                     PageVO vo, 
	                                     Integer cntPerPage,
	                                     
	                                     @RequestParam(value="category", required = false)String category,
	                                     @RequestParam(value="start", required = false, defaultValue = "1") Integer start,
	                                     @RequestParam(value="end", required = false, defaultValue = "10") Integer end)
	  {
		 // model.addAttribute("category",category);
		  String facId = principal.getName();
		  int total = facilityService.getFVolCategoryCount(facId);
		  vo = new PageVO(total, start, end,category, 5);
	      log.info(vo);
	      List<VolActVO> list = facilityService.getVolunteerJoinList(vo,facId);
	      model.addAttribute("volList", list);
	      
	      model.addAttribute("vo", vo);
	      log.info(list);
	      return "facility/myPageVolBefore";
	  }
	//마이페이지(시설 봉사 마무리 후)
		@GetMapping("/fac/volFinish")
		  public String getFacilityVolFinish(Model model,
							                  Principal principal,
							                  PageVO vo, 
							                  
							                  Integer cntPerPage,
							                  @RequestParam(value="category", required = false)String category,
							                  @RequestParam(value="start", required = false, defaultValue = "1") Integer start,
							                  @RequestParam(value="end", required = false, defaultValue = "10") Integer end){
			String facId = principal.getName();
			int total = facilityService.getFinishVolCategoryCount(facId);
			vo = new PageVO(total, start, end,category, 5);
			List<VolActVO> list = facilityService.getVolunteerFinishList(vo,facId);
			model.addAttribute("volList", list);
			model.addAttribute("vo", vo);
			log.info(list);
			return "facility/myPageVolAfter";
		}
		
	
	@GetMapping("/VolAppList")
	@ResponseBody
	public List<VolMemVO> getVolunteerAppList(Model model, Integer volActId) {
		List<VolMemVO> listApp = facilityService.getVolunteerAppList(volActId);
		model.addAttribute("volAppList", listApp);
		return listApp;
	}
	
	@PostMapping("/volAppUpdate")
	@ResponseBody
	public int volAppUpdate(Model model, Integer volActId, String memId) {
		int list = facilityService.volAppUpdate(volActId, memId);
		//model.addAttribute("volAppUpd", list);
		return list;
	}
	
	@PostMapping("/volAppInsert")
	@ResponseBody
	public int volAppInsert(Model model, VolMemVO volMemVO) {
		int list = facilityService.volAppInsert(volMemVO);
		//model.addAttribute("volAppIns", list);
		return list;
	}
	
	@GetMapping("/showJoin")
	public VolMemVO getJoinApp(Model model, Integer volActId) {
		VolMemVO info = facilityService.getJoinApp(volActId);
		model.addAttribute("info", info);
		return info; 
	}

	
	  //회원이 시설봉사 정보 조회
	 
	  @GetMapping("/facilityVolInfo")
	  @ResponseBody
	  public VolActVO getFacVolInfo(Model model, Integer volActId) { 
		  VolActVO info = facilityService.getFacVolInfo(volActId);
		  model.addAttribute("info", info);
		  return info;
	  }
	
	//회원이 시설봉사 신청
	@PostMapping("/joinVol")
	@ResponseBody
	public int getJoinVol(Model model,VolMemVO volMemVO,Principal principal) {
		
		int joinVol = facilityService.insertJoinVolunteer(volMemVO);
		log.info(joinVol);
		return joinVol;
	}
	//시설봉사등록
	@PostMapping("/InvolJoin")
	@ResponseBody
	public int facVolInsert (@RequestParam(value = "files", required = false) MultipartFile[] uploadFiles,VolActVO volActVO, Principal principal) {
		volActVO.setFacId(principal.getName());
		int result = facilityService.InsertFacVol(volActVO);
		log.info("값"+volActVO);
		log.info("값"+result);
		  try { 
			  fileService.uploadFiles(uploadFiles,"p14", volActVO.getVolActId(),volActVO.getFacId());
		  }catch (IOException e) {
			  e.printStackTrace(); 
		   }
		return result;
	}
		
	@PostMapping("/InsertVolHeart")
	@ResponseBody
	public int volHeartInsert (LikeVO likeVO, Principal principal) {
		likeVO.setMemId(principal.getName());
		int result = facilityService.insertVolHeart(likeVO);
		return result;
	}
	
	
	
}
