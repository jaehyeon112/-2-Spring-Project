package com.bongsamaru.member.mapper;

import java.util.List;

import com.bongsamaru.member.MemberVO;

public interface MemberMapper {
	public List<MemberVO> getMemberList(MemberVO MemberVO);
	public MemberVO getMember(String mid);
}
