package com.bongsamaru.user.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.common.VO.UserCategoryVO;
import com.bongsamaru.common.VO.UserFacilityVO;
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
		UserFacilityVO vo = userMapper.login(username);
		System.out.println("vo 는!" + vo);
		if(vo == null) {
			throw new UsernameNotFoundException("no name");
		}
		return new UserDetailVO(vo);
	}
	
	@Override
	public Boolean insertCate(String cate, String name) {
		if(userMapper.insertCate(cate , name)==1) {
			return true;
		}
		return false;
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
			return true;
		}
		return false;
	}
	
	@Override
	public UserVO userList(String mem) {
		return userMapper.userList(mem);
	}
	
	@Override
	@Transactional
	public Boolean userInsert(UserVO vo, List<String> cate) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String result = encoder.encode(vo.getMemPwd());
		vo.setMemPwd(result);
		if(insertUser(vo)) {
			 if (cate != null && !cate.isEmpty()) {
				 for(String one : cate) {
					 insertCate(vo.getMemId(),one);
				 }
			 } 
			 return true;
		};
		
		return false;
	}
	
}
