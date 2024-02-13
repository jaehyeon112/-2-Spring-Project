package com.bongsamaru.user.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Kakao, String> {
    Optional<Kakao> findByKakaoId(Long kakaoId);
}