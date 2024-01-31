package com.bongsamaru.facility.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bongsamaru.facility.VO.FacilityVO;
import com.bongsamaru.facility.VO.FundingVO;
import com.bongsamaru.facility.VO.VolunteerVO;

public interface FacilityService {

	public List<FacilityVO> getFacilityList(@Param("region") String region, @Param("facId") String facId); //시설페이지 리스트
	//public Page<FacilityVO> paging(Pageable pageable); //리스트 페이지
	public FacilityVO getFacilityInfo(String facId);//시설상세페이지
	public List<FundingVO> getFundingList(String facId);//모금진행중 List
	public List<FundingVO> getFundedList(String facId);//모금마감 List
	public List<VolunteerVO> getVolunteerList(String facId);
}
