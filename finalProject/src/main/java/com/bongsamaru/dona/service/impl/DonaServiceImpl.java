package com.bongsamaru.dona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.dona.mapper.DonaMapper;
import com.bongsamaru.dona.service.DonaService;
import com.bongsamaru.dona.service.DonaVO;

@Service
public class DonaServiceImpl implements DonaService {
	

	@Autowired
	DonaMapper donaMapper;
	
	
	@Override
	public List<DonaVO> getDonaList() {
		return donaMapper.getDonaList();
	}
	
	 @Override
	public List<DonaVO> selectRecruitingItems() {
		
		 return donaMapper.selectRecruitingItems();
	}
	 
	 @Override
	public List<DonaVO> selectCompletedItems() {
		 return donaMapper.selectCompletedItems();
	}
	@Override
	public List<DonaVO> getDonaListByCategory(String category) {
		  return donaMapper.getDonaListByCategory(category);
	}
	
//상세페이지	
	@Override
	public DonaVO getDonaDetail(Integer donId, String facId) {
		return donaMapper.getDonaDetail(donId, facId);
	}
//기부자목록	
	@Override
	public List<DonaVO> getDonerList(Integer donId) {
		return donaMapper.getDonerList(donId);
	}
//카테고리	
	@Override
	public List<DonaVO> getCategoryList(String h) {
		return donaMapper.getCategoryList(h);
	}
	
//댓글 리스트
	@Override
	public List<DonaVO> getCommentList(Integer detailCode) {
		return donaMapper.getCommentsList(detailCode);
	}
	
//댓글달기
	@Override
	public int insertComment(DonaVO donaVO) {
		int result = donaMapper.insertComment(donaVO);
		if(result == 1) {
			return donaVO.getCommId();
		}else {
			return -1; 
		}
	}
	
//기부 등록폼	
//	@Override
//	public int insertDonation(DonaVO donaVO) {
//		int result = donaMapper.insertDonation(donaVO);
//		
//		if(result == 1) {
//			return donaVO.getDonId();
//		}else {
//			return -1;
//		}
		
// 기부등록(상세) 폼		
//	@Override
//	public int insertDonDetail(DonaVO donaVO) {
//	int result = donaMapper.insertDonDetail(donaVO);
//		
//		if(result == 1) {
//			return donaVO.getDonId();
//		}else {
//			return -1;
//		}
//	}
	
	//기부등록폼
	@Transactional
	@Override
	public int insertDonation(DonaVO donaVO) {
		donaMapper.insertDonation(donaVO);
		return donaMapper.insertDonDetail(donaVO);
	}

	
			//	@Transactional
			//	@Override
			//	public int insertRemittance(RemittanceVO remittanceVO) {
			//		//송금 확인코드 변경
			//		userMapper.updatePaidCode(remittanceVO.getDonId());
			//		//송금 테이블에 삽입
			//		return userMapper.insertRemittance(remittanceVO);
			//	}
	
	
//결제프로세스	
	@Override
	public int paymentProcess(DonaVO donaVO) {
		int result = donaMapper.paymentProcess(donaVO);
		
		if(result == 1 ) {
			return donaVO.getDonLedId();
		}else {
		return -1;
		}
	}

	@Override
	public int insertDonDetail(DonaVO donaVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
