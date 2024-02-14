package com.bongsamaru.bongsa.mapper;

import java.util.List;

import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;

public interface BongsaMapper {
	public List<VolActVO> facilityList(PageVO vo);
	public List<VolunteerVO> volList(PageVO vo);
	public int cntFacilityList(PageVO vo);
}
