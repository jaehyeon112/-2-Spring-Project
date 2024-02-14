package com.bongsamaru.bongsa.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolunteerVO;

public interface BongsaMapper {
	public List<VolActVO> facilityList(@Param("pageVO") PageVO pageVO, 
            @Param("startDate")Date startDate,@Param("endDate")Date endDate);
	public List<VolunteerVO> volList(PageVO vo);
	public int cntFacilityList(PageVO vo);
}
