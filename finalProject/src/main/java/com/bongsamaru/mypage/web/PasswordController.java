package com.bongsamaru.mypage.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bongsamaru.user.service.UserDetailVO;

/**
 * 프로필 수정을 위한 비밀번호 인증
 * @author 나채현
 *
 */
@Controller
public class PasswordController {
	@Autowired
    private PasswordEncoder passwordEncoder;
    
	/**
	 * 비밀번호 인증 페이지 생성
	 * @return my/pass
	 */
	@GetMapping("/pass")
	public String getPasswordForm() {
	    return "my/pass";
	}

	/**
	 * 비밀번호 인증 절차
	 * @param currentPassword
	 * @return
	 */
	@PostMapping("/passwordCheck")
	public ResponseEntity<?> passwordCheck(@RequestParam("currentPassword") String currentPassword) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	        Object principal = auth.getPrincipal();

	        if (principal instanceof UserDetails) {
	            UserDetailVO userDetailVO = (UserDetailVO) principal;

	            // 현재 로그인한 사용자의 비밀번호와 입력한 비밀번호를 비교합니다.
	            if (passwordEncoder.matches(currentPassword, userDetailVO.getPassword())) {
	                // 비밀번호가 일치하는 경우
	                return ResponseEntity.ok().body("비밀번호가 일치합니다.");
	            } else {
	                // 비밀번호가 일치하지 않는 경우
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
	            }
	        }
	    }

	    // 인증되지 않은 경우
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않았습니다.");
	}
}
