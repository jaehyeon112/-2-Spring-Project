package com.bongsamaru.mypage.mapper;

import java.util.List;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.mypage.service.DonledgerVO;
import com.bongsamaru.mypage.service.HeartVO;

public interface MypageMapper {
	
	// 마음온도 조회
	public Double getHeart(String memId);
	
	// 프로필 조회
	public List<UserVO> getProfile(String memId);
	
	// 기부내역 조회
	public List<DonledgerVO> getGibuList(String memId);
	
	// 기부 총금액 조회
	public Integer getSumAmt(String memId);
	
	// 기부 총횟수 조회
	public Integer getGibuCount(String memId);
	
	// 이메일 변경
	public int updateEmail(UserVO userVO);
	
	// insert Heart
	public int insertHeart(HeartVO vo);

}
