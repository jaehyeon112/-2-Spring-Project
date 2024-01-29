package com.bongsamaru.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bongsamaru.member.MemberVO;
import com.bongsamaru.member.UserDetailVO;
import com.bongsamaru.member.mapper.MemberMapper;
import com.bongsamaru.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService,UserDetailsService{

@Autowired
MemberMapper memberMapper;
	
	@Override
	public MemberVO getMember(String mid) {
		return memberMapper.getMember(mid); 
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = memberMapper.getMember(username);
		if(vo == null) {
			throw new UsernameNotFoundException("no name");
		}
		return new UserDetailVO(vo);
	}

}
