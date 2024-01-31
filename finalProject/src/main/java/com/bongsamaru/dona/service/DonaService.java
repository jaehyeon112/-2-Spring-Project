package com.bongsamaru.dona.service;

import java.util.List;


public interface DonaService {
	//메인페이지 전체리스트
	List<DonaVO> getDonaList();
	
	
	//상세페이지 조회 1
	public DonaVO getDonaDetail(Integer donId, String facId);
	

	
}
