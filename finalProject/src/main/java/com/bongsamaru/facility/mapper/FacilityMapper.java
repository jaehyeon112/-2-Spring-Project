package com.bongsamaru.facility.mapper;

import java.util.List;

import com.bongsamaru.facility.VO.FacilityVO;

public interface FacilityMapper {
	
	public List<FacilityVO> getFacilityList();
	public FacilityVO getFacilityInfo();	
	
}
