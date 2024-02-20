package com.bongsamaru.user.service;

import java.util.List;

import com.bongsamaru.common.VO.AlertVO;
import com.bongsamaru.common.VO.CountVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.UserCategoryVO;
import com.bongsamaru.common.VO.UserVO;


public interface UserService {
	public UserVO userList(String mem);
	public Boolean countMemId(String memId);
	public Boolean countMemNick(String memNick);
	public Boolean insertUser(UserVO vo);
	public List<UserCategoryVO> userCategoty();
	public Boolean insertCate(String cate, String name);
	public Boolean userInsert(UserVO vo, List<String> cate);
	public Boolean countBizNum(String num);
	public Boolean insertFac(FacilityVO vo);
	public List<AlertVO> listAlert(String memId);
	public List<CountVO> volKing();
}
