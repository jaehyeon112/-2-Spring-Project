package com.bongsamaru.user.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Kakao, String> {
    // 여기에 필요한 JPA 쿼리 메소드를 추가할 수 있습니다.
	public int countByKakaoId(String kakaoId);
}