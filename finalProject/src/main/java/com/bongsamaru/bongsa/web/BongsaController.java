package com.bongsamaru.bongsa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.common.service.CommonService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BongsaController {
	@Autowired
	AdminService userService;
	
	@Autowired
	BongsaService bongsaService;
	
	@Autowired
	CommonService commonService;
	
	@GetMapping("/daily")
	public String goToDaily() {
		return "bongsa/DailyVol";
	}

	@GetMapping("/Group")
	public String goToGroup(Model model, VolunteerVO vo) {
		List<VolunteerVO> list = userService.meetingList(vo);
		model.addAttribute("meet", list);
		List<TagVO> tags = userService.tagList();
		model.addAttribute("tag", tags);
		return "bongsa/GroupVol";
	}
	@GetMapping("/FacilityVol")
	public String goToFacility(PageVO vo, Model model,
	        @RequestParam(value="searchKeyword", required = false) String searchKeyword,
	        @RequestParam(value="category", required = false) String category,
	        @RequestParam(value="location", required = false) String zip,
	        @RequestParam(value="start", required = false, defaultValue = "1") Integer start,
	        @RequestParam(value="end", required = false, defaultValue = "8") Integer end) {

	    // 필터링 조건 설정
	    if (category != null && !category.isEmpty()) {
	        vo.setCategory(category);
	    }
	    if (zip != null && !zip.isEmpty()) {
	        vo.setVolZip2(zip);
	    }
	    log.info(zip);
	    
	    // 전체 개수 조회
	    int total = bongsaService.cntFacilityList(vo);
	    vo = new PageVO(total, start, end, category, searchKeyword, 8 ,zip);
	    List<VolActVO> list = bongsaService.facilityList(vo);
	    log.info(vo);
	    model.addAttribute("cate", commonService.selectSubCode("f"));
	    model.addAttribute("fac", list);
	    model.addAttribute("vo", vo);
	    model.addAttribute("location", commonService.selectSubCode("z"));
	    return "bongsa/FacilityVol";
	}
	
}
