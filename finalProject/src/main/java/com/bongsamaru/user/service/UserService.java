package com.bongsamaru.user.service;

import java.util.List;

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
	
}
