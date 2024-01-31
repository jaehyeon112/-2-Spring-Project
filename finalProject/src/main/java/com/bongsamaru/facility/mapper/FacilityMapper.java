package com.bongsamaru.facility.mapper;

import java.util.List;

import com.bongsamaru.facility.VO.FacilityVO;
import com.bongsamaru.facility.VO.FundingVO;
import com.bongsamaru.facility.VO.VolunteerVO;

public interface FacilityMapper {
	
	public List<FacilityVO> getFacilityList();
	public FacilityVO getFacilityInfo(String facId);
	public List<FundingVO> getfundingList(String facId);
	public List<FundingVO> getfundedList(String facId);
	public List<VolunteerVO> getVolList(String facId);
}
