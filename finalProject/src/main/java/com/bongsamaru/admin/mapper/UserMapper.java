package com.bongsamaru.admin.mapper;

import com.bongsamaru.admin.service.UserVO;

public interface UserMapper {
	public UserVO userList(String mem);
	public int countMemId(String MemId);
	public int countMemNick(String memNick);
	public int userSignUp(UserVO vo);
}
