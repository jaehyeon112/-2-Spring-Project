package com.bongsamaru.user.mapper;

import java.util.List;

import com.bongsamaru.common.UserCategoryVO;
import com.bongsamaru.common.UserVO;

public interface UserMapper {
	public UserVO userList(String mem);
	public int countMemId(String MemId);
	public int countMemNick(String memNick);
	public int userSignUp(UserVO vo);
	public List<UserCategoryVO> categoryList();
}
