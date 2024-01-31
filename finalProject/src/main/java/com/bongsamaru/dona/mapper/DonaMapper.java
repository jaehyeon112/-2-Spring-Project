package com.bongsamaru.dona.mapper;

import java.util.List;

import com.bongsamaru.dona.service.DonaVO;


public interface DonaMapper {
	
	// 메인페이지 전체리스트
		List<DonaVO> getDonaList();
		
	// 상세페이지1 (don_id)
		public DonaVO getDonaDetail(Integer donId, String facId);
		

		
		
		
}


