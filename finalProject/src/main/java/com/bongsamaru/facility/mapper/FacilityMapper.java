package com.bongsamaru.facility.mapper;

import java.util.List;

import com.bongsamaru.facility.VO.FacilityVO;
import com.bongsamaru.facility.VO.FundingVO;

public interface FacilityMapper {
	
	public List<FacilityVO> getFacilityList();
	public FacilityVO getFacilityInfo(FacilityVO facilityVO);
	public List<FundingVO> getfundingList();
	public List<FundingVO> getfundedList();
	
}
