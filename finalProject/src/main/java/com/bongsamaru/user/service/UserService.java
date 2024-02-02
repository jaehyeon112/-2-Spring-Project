package com.bongsamaru.user.service;

import com.bongsamaru.common.VO.UserVO;

public interface UserService {
	public UserVO userList(String mem);
	public Boolean countMemId(String memId);
	public Boolean countMemNick(String memNick);
	public Boolean insertUser(UserVO vo);
}
