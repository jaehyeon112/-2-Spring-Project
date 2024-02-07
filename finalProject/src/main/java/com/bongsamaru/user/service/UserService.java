package com.bongsamaru.user.service;


import com.bongsamaru.common.VO.CategoryLikeVO;
import com.bongsamaru.common.VO.UserVO;

import java.util.List;


public interface UserService {
	public UserVO userList(String mem);
	public Boolean countMemId(String memId);
	public Boolean countMemNick(String memNick);
	public Boolean insertUser(UserVO vo);
	public List<CategoryLikeVO> selectCategory();
}
