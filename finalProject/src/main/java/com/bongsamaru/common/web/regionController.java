package com.bongsamaru.common.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bongsamaru.common.VO.RegionVO;
@Controller
public class regionController {
	@ModelAttribute("regions")
	public List<RegionVO> selectRegions() {
	    
		List<RegionVO> regions = new ArrayList<>();
	    regions.add(new RegionVO("SEOUL", "서울특별시"));
	    regions.add(new RegionVO("INCHEON", "인천광역시"));
	    regions.add(new RegionVO("DEJEON", "대전광역시"));
	    regions.add(new RegionVO("Gwangju", "광주광역시"));
	    regions.add(new RegionVO("DAEGU", "대구광역시"));
	    regions.add(new RegionVO("BUSAN", "부산광역시"));
	    regions.add(new RegionVO("ULSAN", "울산광역시"));
	    regions.add(new RegionVO("gyeonggi-do", "경기도"));
	    regions.add(new RegionVO("Gangwon-do", "강원도"));
	    regions.add(new RegionVO("chungcheongbugdo", "충청북도"));
	    regions.add(new RegionVO("chungcheongnamdo", "충청남도"));
	    regions.add(new RegionVO("jeonlabugdo", "전라북도"));
	    regions.add(new RegionVO("jeonlanamdo", "전라남도"));
	    regions.add(new RegionVO("gyeongsangbugdo", "경상북도"));
	    regions.add(new RegionVO("gyeongsangnamdo", "경상남도"));
	    regions.add(new RegionVO("JEJUDO", "제주도"));
	    
	    return regions;
	}
}
