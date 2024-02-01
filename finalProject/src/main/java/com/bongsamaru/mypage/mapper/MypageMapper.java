package com.bongsamaru.mypage.mapper;

import com.bongsamaru.admin.service.UserVO;

public interface MypageMapper {
	
	// 마음온도 조회
	public Double getHeart(String memId);
	
	// 기부 총금액 조회
	public Integer getSumAmt(String memId);
	
	// 기부 총횟수 조회
	public Integer getGibuCount(String memId);
	
	// 이메일 변경
	public int updateEmail(UserVO userVO);
}
