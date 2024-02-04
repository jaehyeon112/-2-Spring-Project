package com.bongsamaru.dona.mapper;

import java.util.List;

import com.bongsamaru.dona.service.DonaVO;


public interface DonaMapper {
	
	// 메인페이지 전체리스트
		List<DonaVO> getDonaList();
		
	// 상세페이지1 (don_id)
		public DonaVO getDonaDetail(Integer donId, String facId);
	
	// 기부자목록 in 상세페이지
		public List<DonaVO> getDonerList(Integer donId);
		
	//댓글 - 전체리스트 
		public List<DonaVO> getCommentsList(Integer donId);
		
	//댓글 insert
		public int insertComment(DonaVO donaVO);
		
	//카테고리
		List<DonaVO> getCategoryList(String h);

	
	//기부등록폼 
		public int insertDonation(DonaVO donaVO);
	
	//결제프로세스
	 public int paymentProcess(DonaVO donaVO);
		
		
}


