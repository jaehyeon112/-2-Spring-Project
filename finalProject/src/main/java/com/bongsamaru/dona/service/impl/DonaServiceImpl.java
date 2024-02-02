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

//댓글삽입	
	@Override
	public int insertComment(Integer donId, DonaVO donaVO) {
//		int result = donaMapper.insertComments(donaVO);
//		
//			if(result == 1) {
//				return donaVO.getDonId();
//			}else {
//				return -1;
//			}
		 try {
		        donaVO.setDonId(donId); // 댓글을 등록할 기부 프로젝트의 ID 설정
		        return donaMapper.insertComment(donaVO);
		    } catch (Exception e) {
		        throw new RuntimeException("댓글 등록 중 오류가 발생했습니다.", e);
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
}
