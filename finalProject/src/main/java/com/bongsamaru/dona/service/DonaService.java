package com.bongsamaru.dona.service;

import java.util.List;


public interface DonaService {
	//메인페이지 전체리스트
	List<DonaVO> getDonaList();
	
	
	//상세페이지 조회 1
	public DonaVO getDonaDetail(Integer donId, String facId);
	
	//기부-카테고리 list
	public List<DonaVO> getCategoryList();

	
	// 결제 완료자 리스트
	public DonaVO getDoner(Integer donId);
	
	
	//댓글 상세페이지 안에
	
	
	
	//등록폼 insert
	public int insertDonation(DonaVO donaVO);
	
	
}
