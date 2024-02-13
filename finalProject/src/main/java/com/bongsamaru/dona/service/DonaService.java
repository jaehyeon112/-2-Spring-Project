package com.bongsamaru.dona.service;

import java.util.List;


public interface DonaService {
	//메인페이지 전체리스트
	List<DonaVO> getDonaList();
	//모금중
	List<DonaVO> selectRecruitingItems();
	//모금완료
	List<DonaVO> selectCompletedItems();
	//카테고리별리스트
	List<DonaVO> getDonaListByCategory(String category);
	
	//상세페이지 조회 1
	public DonaVO getDonaDetail(Integer donId, String facId);
	
	//기부-카테고리 list
	public List<DonaVO> getCategoryList(String h);

	
	// 결제 완료자 리스트
	public List<DonaVO> getDonerList(Integer donId);
	
	
	//댓글 리스트
	public List<DonaVO> getCommentList(Integer detailCode);
	
	//댓글 등록하기
	public int insertComment(DonaVO donaVO);
	
	//등록폼 insert
	public int insertDonation(DonaVO donaVO);
	public int insertDonDetail(DonaVO donaVO);
	
	
	//등록 후기 insert
	public int insertReview(DonaVO donaVO);
	public int insertReviewDetail(DonaVO donaVO);
	
	
	//결제하기
	public int paymentProcess(DonaVO donaVO);
}
