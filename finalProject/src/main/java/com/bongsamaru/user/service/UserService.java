package com.bongsamaru.user.service;

import java.util.List;

import com.bongsamaru.common.UserCategoryVO;
import com.bongsamaru.common.UserVO;

public interface UserService {
	public UserVO userList(String mem);
	public Boolean countMemId(String memId);
	public Boolean countMemNick(String memNick);
	public Boolean insertUser(UserVO vo);
	public List<UserCategoryVO> userCategoty();
}
