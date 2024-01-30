package com.bongsamaru.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.admin.mapper.UserMapper;
import com.bongsamaru.admin.service.UserService;
import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.UserVO;
import com.bongsamaru.common.VolunteerVO;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;

	@Override
	public List<UserVO> getUserList(String memStat) {
		return userMapper.getUserList(memStat);
	}
	
	@Override
	public List<BoardVO> getBoardList(String category) {
		return userMapper.getBoardList(category);
	}
	
	@Override
	public List<FacilityVO> getFacilityList() {
		return userMapper.getFacilityList();
	}
	
	@Override
	public List<DonationVO> getDonationList() {
		return userMapper.getDonationList();
	}
	
	@Override
	public List<FaqVO> getFaqList() {
		return userMapper.getFaqList();
	}

	@Override
	public int insertNotice(BoardVO boardVO) {
		return userMapper.insertNotice(boardVO);
	}

	@Override
	public UserVO getUserOne(String memId) {
		return userMapper.getUserOne(memId);
	}

	@Override
	public VolunteerVO volCount(String memId) {
		return userMapper.volCount(memId);
	}
	
	
}
