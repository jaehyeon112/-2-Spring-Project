package com.bongsamaru.bongsa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.bongsa.mapper.BongsaMapper;
import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.common.VO.VolActVO;

@Service
public class BonsaServiceImpl implements BongsaService {
	@Autowired
	BongsaMapper bongsaMapper;
	
	@Override
	public List<VolActVO> facilityList() {
		return bongsaMapper.facilityList();
	}
}
