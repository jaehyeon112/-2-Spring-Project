package com.bongsamaru.mypage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.mypage.mapper.MypageMapper;
import com.bongsamaru.mypage.service.DonledgerVO;
import com.bongsamaru.mypage.service.HeartVO;
import com.bongsamaru.mypage.service.MypageService;

@Service
public class MypageServiceImpl implements MypageService{
	
	@Autowired
	MypageMapper mypageMapper;
	
	@Override
	public int updateEmail(UserVO userVO) {
		// TODO Auto-generated method stub
		return mypageMapper.updateEmail(userVO);
	}
	
	@Override
	public Double getHeart(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getHeart(memId);
	}
	
	@Override
	public Integer getSumAmt(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getSumAmt(memId);
	}
	
	@Override
	public Integer getGibuCount(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getGibuCount(memId);
	}
	
	@Override
	public List<DonledgerVO> getGibuList(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getGibuList(memId);
	}
<<<<<<< HEAD
	
	@Override
	public List<UserVO> getProfile(String memId) {
		// TODO Auto-generated method stub
		return mypageMapper.getProfile(memId);
	}
=======
	@Override
	public boolean insertHeart(HeartVO vo) {
		if(mypageMapper.insertHeart(vo)==1) {
			return true;
		}
		return false; 
	}	
	
>>>>>>> develop
}
