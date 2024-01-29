package com.bongsamaru.facility.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bongsamaru.facility.Service.FacilityService;
import com.bongsamaru.facility.VO.FacilityVO;
import com.bongsamaru.facility.mapper.FacilityMapper;

public class FacilityServiceImpl implements FacilityService {

	@Autowired
	FacilityMapper mapper;
	
	@Override
	public List<FacilityVO> SelectBoxList() {	
		return mapper.getSelectBox();
	}
}


