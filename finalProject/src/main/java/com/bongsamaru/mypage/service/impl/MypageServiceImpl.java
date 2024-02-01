package com.bongsamaru.mypage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.admin.service.UserVO;
import com.bongsamaru.mypage.mapper.MypageMapper;
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
}
