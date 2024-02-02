package com.bongsamaru.user.mapper;

import com.bongsamaru.common.VO.UserVO;

public interface UserMapper {
	public UserVO userList(String mem);
	public int countMemId(String MemId);
	public int countMemNick(String memNick);
	public int userSignUp(UserVO vo);
}
