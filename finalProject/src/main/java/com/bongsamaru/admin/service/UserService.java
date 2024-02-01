package com.bongsamaru.admin.service;

import java.util.List;

public interface UserService {
	public UserVO userList(String mem);
	public Boolean countMemId(String memId);
	public Boolean countMemNick(String memNick);
}
