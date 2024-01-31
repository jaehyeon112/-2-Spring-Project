package com.bongsamaru.facility.Service;

import java.util.List;

import com.bongsamaru.facility.VO.FacilityVO;
import com.bongsamaru.facility.VO.FundingVO;
import com.bongsamaru.facility.VO.VolunteerVO;

public interface FacilityService {

	public List<FacilityVO> getFacilityList(); //시설페이지 리스트
	public FacilityVO getFacilityInfo(String facId);//시설상세페이지
	public List<FundingVO> getFundingList(String facId);//모금진행중 List
	public List<FundingVO> getFundedList(String facId);//모금마감 List
	public List<VolunteerVO> getVolunteerList(String facId);
}
