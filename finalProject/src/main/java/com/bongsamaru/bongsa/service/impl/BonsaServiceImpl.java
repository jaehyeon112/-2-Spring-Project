package com.bongsamaru.bongsa.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.bongsa.mapper.BongsaMapper;
import com.bongsamaru.bongsa.service.BongsaService;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;

@Service
public class BonsaServiceImpl implements BongsaService {
	@Autowired
	BongsaMapper bongsaMapper;
	
	@Override
	public List<VolActVO> facilityList(PageVO vo, Date start, Date end) {
	    if (start == null) {
	        Calendar startCal = Calendar.getInstance();
	        startCal.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
	        startCal.set(Calendar.MILLISECOND, 0);
	        start = startCal.getTime();
	    }

	    // end가 null인 경우 2050년 12월 31일로 설정
	    if (end == null) {
	        Calendar endCal = Calendar.getInstance();
	        endCal.set(2050, Calendar.DECEMBER, 31, 23, 59, 59);
	        endCal.set(Calendar.MILLISECOND, 999);
	        end = endCal.getTime();
	    }
		return bongsaMapper.facilityList(vo,start,end);
	}
	
	@Override
	public int cntFacilityList(PageVO vo) {
		return bongsaMapper.cntFacilityList(vo);
	}
}
