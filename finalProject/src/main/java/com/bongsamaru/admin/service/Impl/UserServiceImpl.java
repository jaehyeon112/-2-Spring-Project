package com.bongsamaru.admin.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.admin.mapper.UserMapper;
import com.bongsamaru.admin.service.UserService;
import com.bongsamaru.admin.service.UserVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;

	@Override
	public UserVO getUserList(String memStat) {
		return userMapper.getUserList(memStat);
	}

	
	
}
