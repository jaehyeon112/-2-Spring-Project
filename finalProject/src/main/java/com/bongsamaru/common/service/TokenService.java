package com.bongsamaru.common.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.common.VO.Token;
import com.bongsamaru.common.service.repository.TokenRepository;

import lombok.extern.log4j.Log4j;
@Service
@Log4j
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    @Transactional
    public Token createToken(String userId, String tokenValue) {
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(30); // 현재 시간으로부터 30분 후

        Token token = new Token();
        token.setToken(tokenValue);
        token.setUserId(userId);
        token.setExpiryDate(expiryDate);
        // 여기서 userId를 토큰과 연결하는 로직도 추가할 수 있습니다.
        
        tokenRepository.save(token);
        return token;
    }
    
    public boolean validateToken(String token) {
    	log.info(token);
        // 토큰으로 데이터베이스에서 토큰 정보를 조회
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity != null) {
            log.info("TokenEntity: {}" + tokenEntity);
        } else {
            log.info("Token not found or is null");
        }
        // 토큰이 존재하지 않는 경우
        if (tokenEntity == null) {
            return false;
        }
        
        // 토큰의 만료 시간이 현재 시간보다 이전인 경우 (즉, 토큰이 만료된 경우)
        if (tokenEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            return false;
        }
        
        // 여기까지 문제가 없다면 토큰이 유효한 것으로 간주
        return true;
    }
    
    public String getUserIdFromToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity != null && tokenEntity.getExpiryDate().isAfter(LocalDateTime.now())) {
            return tokenEntity.getUserId();
        }
        return null;
    }
    
}
