package com.bongsamaru.facility.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.facility.VO.FacilityVO;
import com.bongsamaru.facility.VO.FundingVO;
import com.bongsamaru.facility.VO.VolunteerVO;
import com.bongsamaru.facility.mapper.FacilityMapper;
@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	FacilityMapper mapper;

	@Override
	public List<FacilityVO> getFacilityList() {
		return mapper.getFacilityList();
	}

	@Override
	public FacilityVO getFacilityInfo(String facId) {
		return mapper.getFacilityInfo(facId);
	}

	@Override
	public List<FundingVO> getFundingList(String facId) {
		return mapper.getfundingList(facId);
	}

	@Override
	public List<FundingVO> getFundedList(String facId) {
		return mapper.getfundedList(facId);
	}

	@Override
	public List<VolunteerVO> getVolunteerList(String facId) {
		
		return mapper.getVolList(facId);
	}
}


