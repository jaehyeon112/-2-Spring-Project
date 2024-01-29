package com.bongsamaru.facility.Service;

import java.util.List;

import com.bongsamaru.facility.VO.FacilityVO;

public interface FacilityService {

	public List<FacilityVO> getFacilityList(); //시설페이지 리스트
	public FacilityVO getFacilityInfo(FacilityVO facilityVO);//시설상세페이지
}
