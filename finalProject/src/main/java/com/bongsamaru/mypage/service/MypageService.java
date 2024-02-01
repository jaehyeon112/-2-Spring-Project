/*
 * package com.bongsamaru.mypage.service;
 * 
 * import org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.bongsamaru.admin.service.UserVO; import
 * com.bongsamaru.mypage.domain.Member; import
 * com.bongsamaru.mypage.domain.MemberRepository;
 * 
 * @Service public class MypageService { MemberRepository memberRepository;
 * 
 * @Transactional public void updateEmail(UserVO userVO) { Member member =
 * memberRepository.findById(userVO.getMemId()) .orElseThrow(() -> new
 * IllegalArgumentException("해당하는 회원을 찾을 수 없습니다."));
 * memberRepository.save(member.getMemEmail()); }
 * 
 * }
 */