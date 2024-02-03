package com.bongsamaru.user.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bongsamaru.common.UserCategoryVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.user.mapper.UserMapper;
import com.bongsamaru.user.service.UserDetailVO;
import com.bongsamaru.user.service.UserService;
@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO vo = userMapper.userList(username);
		System.out.println("vo 는!" + vo);
		if(vo == null) {
			throw new UsernameNotFoundException("no name");
		}
		return new UserDetailVO(vo);
	}
	
	
	
	@Override
	public List<UserCategoryVO> userCategoty() {
		return userMapper.categoryList();
	}

	@Override
	public Boolean countMemId(String memId) {
		if(userMapper.countMemId(memId) == 1){
			return false;
		}
		return true;
	}
	
	@Override
	public Boolean countMemNick(String memNick) {
		if(userMapper.countMemNick(memNick) == 1){
			return false;
		}
		return true;
	}
	@Override
	public Boolean insertUser(UserVO vo) {
		if(userMapper.userSignUp(vo) == 1){
			return false;
		}
		return true;
	}
	@Override
	public UserVO userList(String mem) {
		return userMapper.userList(mem);
	}
	
	
}
