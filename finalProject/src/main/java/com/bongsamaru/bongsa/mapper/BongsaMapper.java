package com.bongsamaru.bongsa.mapper;

import java.util.List;

import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;

public interface BongsaMapper {
	public List<VolActVO> facilityList(PageVO vo);
	public int cntFacilityList(PageVO vo);
}
