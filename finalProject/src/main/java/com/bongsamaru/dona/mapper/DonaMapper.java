package com.bongsamaru.dona.mapper;

import java.util.List;

import com.bongsamaru.dona.service.DonaVO;


public interface DonaMapper {
	
	// 메인페이지 전체리스트
		List<DonaVO> getDonaList();
		
	// 상세페이지1 (don_id)
		public DonaVO getDonaDetail(Integer donId, String facId);
	
	// 기부자목록 in 상세페이지
		public DonaVO getDoner(Integer donId);
		
	//댓글 - 전체리스트 
		public List<DonaVO> getCommentsList();
		
	//댓글 insert
		public int insertComments(DonaVO donaVO);
		
	//카테고리
		List<DonaVO> getCategoryList();

	
	//기부등록폼 
		public int insertDonation(DonaVO donaVO);
		
		
		
}


