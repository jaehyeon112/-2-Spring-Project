package com.bongsamaru.admin.service;

public interface UserService {
	public UserVO userList(String mem);
	public Boolean countMemId(String memId);
	public Boolean countMemNick(String memNick);
	public Boolean insertUser(UserVO vo);
}
