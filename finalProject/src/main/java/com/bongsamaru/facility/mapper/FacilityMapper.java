package com.bongsamaru.facility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;

public interface FacilityMapper {
	
	public List<FacilityVO> getFacilityList(@Param("region") String region, @Param("facId") String facId);
	public FacilityVO getFacilityInfo(String facId);
	public List<FundingVO> getfundingList(String facId);
	public List<FundingVO> getfundedList(String facId);
	public List<VolunteerVO> getVolList(String facId);
	public List<DonaVO> getDonaInfo();
}
