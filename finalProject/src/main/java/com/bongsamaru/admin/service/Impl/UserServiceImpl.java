package com.bongsamaru.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.admin.mapper.UserMapper;
import com.bongsamaru.admin.service.UserService;
import com.bongsamaru.admin.service.UserVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;

	@Override
	public List<UserVO> getUserList(String memStat) {
		return userMapper.getUserList(memStat);
	}
	
	@Override
	public List<FacilityVO> getFacilityList() {
		return userMapper.getFacilityList();
	}
	
	@Override
	public List<DonationVO> getDonationList() {
		return userMapper.getDonationList();
	}
	
	
}
