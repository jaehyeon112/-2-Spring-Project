package com.bongsamaru.mypage.service;


import java.util.List;

import com.bongsamaru.common.VO.UserVO;

public interface MypageService {
	
	public Double getHeart(String memId);
	public int updateEmail(UserVO userVO);
	public Integer getSumAmt(String memId);
	public Integer getGibuCount(String memId);
	public List<DonledgerVO> getGibuList(String memId);
	public List<UserVO> getProfile(String memId);
}
