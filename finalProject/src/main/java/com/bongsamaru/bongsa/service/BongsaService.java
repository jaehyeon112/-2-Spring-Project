package com.bongsamaru.bongsa.service;

import java.util.Date;
import java.util.List;

import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;

public interface BongsaService {
	public List<VolActVO> facilityList(PageVO vo, Date start, Date end);
	public int cntFacilityList(PageVO vo);
}
