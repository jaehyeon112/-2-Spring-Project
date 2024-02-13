package com.bongsamaru.user.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bongsamaru.user.service.UserRepository;
import com.bongsamaru.user.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping
public class MemberOauth2Controller {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository user;
	
	
    @Value("${kakao.client.id}")
    private String kakaoClientId;

    @Value("${kakao.redirect.url}")
    private String kakaoRedirectUrl;

    @Value("${kakao.client.secret}")
    private String secret_code;

    @GetMapping(value = "/login2")
    public String kakaoOauthRedirect(@RequestParam String code, Model model, HttpServletResponse response,  RedirectAttributes redirectAttributes) {
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId); 
        params.add("redirect_uri", kakaoRedirectUrl); 
        params.add("code", code);
        params.add("client_secret", secret_code); 
        
        System.out.println(params);
        // HttpHeader와 HttpBody를 HttpEntity에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // HTTP 요청 - POST방식 - 토큰 응답 받기
        ResponseEntity<String> tokenResponse = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );
        
        // 액세스 토큰 추출
        String accessToken ="";
		try {
			accessToken = extractAccessToken(tokenResponse.getBody());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

        // 사용자 정보 요청을 위한 헤더 설정
        HttpHeaders userInfoHeaders = new HttpHeaders();
        userInfoHeaders.add("Authorization", "Bearer " + accessToken);
        userInfoHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpEntity 객체 생성 (사용자 정보 요청)
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(userInfoHeaders);

        // HTTP 요청 - POST방식 - 사용자 정보 응답 받기
        ResponseEntity<String> userInfoResponse = rt.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET,
            kakaoUserInfoRequest,
            String.class
        );
        
        
        // 사용자 정보 응답 반환
        model.addAttribute("kakao",userInfoResponse.getBody());
        if(userService.countMemId(kakaoClientId)) {
        	System.out.println("회원가입진행!");
        	
        }else{
        	System.out.println("로그인진행!");
        }
        
        return userInfoResponse.getBody();
        
    }

    private String extractAccessToken(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);
        return rootNode.path("access_token").asText();
    }
}
