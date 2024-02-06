package com.bongsamaru.facility.ServiceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FundingVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.facility.mapper.FacilityMapper;
@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	FacilityMapper mapper;

	@Override
	public List<FacilityVO> getFacilityList(String region, String facId) {
		return mapper.getFacilityList(region,facId);
	}
	
	/*
	 * @Override public Page<FacilityVO> paging(Pageable pageable) { int page =
	 * pageable.getPageNumber()-1; int pageLimit = 6; Page<FacilityEntity>
	 * facilityEntities = facilityRepository.findAll(PageRequest.of(page,
	 * pageLimit,Sort.by(Sort.Direction.DESC,.facilityEntities..))) return null; }
	 */
	
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

	@Override
	public List<DonaVO> getDonaList() {
		
		return mapper.getDonaInfo();
	}

	
}


