package com.bongsamaru.dona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<DonaVO> getCommentList(Integer donId) {
		return donaMapper.getCommentsList(donId);
	}

	@Override
	public int insertComment(DonaVO donaVO) {
		int result = donaMapper.insertComment(donaVO);
		if(result == 1) {
			return donaVO.getCommId();
		}else {
			return -1; 
		}
	}
	

	
	
	@Override
	public int insertDonation(DonaVO donaVO) {
		int result = donaMapper.insertDonation(donaVO);
		
		if(result == 1) {
			return donaVO.getDonId();
		}else {
			return -1;
		}
		
	}
	@Override
	public int insertDonDetail(DonaVO donaVO) {
	int result = donaMapper.insertDonDetail(donaVO);
		
		if(result == 1) {
			return donaVO.getDonId();
		}else {
			return -1;
		}
	}
	
	@Override
	public int paymentProcess(DonaVO donaVO) {
		int result = donaMapper.paymentProcess(donaVO);
		
		if(result == 1 ) {
			return donaVO.getDonLedId();
		}else {
		return -1;
		}
	}
	
	
	
	
}
