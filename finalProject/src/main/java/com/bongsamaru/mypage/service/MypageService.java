package com.bongsamaru.mypage.service;


import java.util.List;

import com.bongsamaru.admin.service.UserVO;

public interface MypageService {
	
	public Double getHeart(String memId);
	public int updateEmail(UserVO userVO);
	public Integer getSumAmt(String memId);
	public Integer getGibuCount(String memId);
	public List<DonledgerVO> getGibuList(String memId);
}
