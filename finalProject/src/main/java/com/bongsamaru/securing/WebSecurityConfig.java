package com.bongsamaru.securing;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.bongsamaru.securing.config.CustomOAuth2UserService;
import com.bongsamaru.securing.filter.AdditionalInfoFilter;
import com.bongsamaru.user.service.AuthSuccessHandler;
import com.bongsamaru.user.service.UserSuccessHandler;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final CustomOAuth2UserService customOAuthUserService;
	private final AdditionalInfoFilter additionalInfoFilter;
	@Autowired
	private DataSource dataSource; // 데이터 소스 주입
	
	@Bean
	public BCryptPasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();	
	}

    @Value("${secret}")
    private String secret;	
	
	
	@Bean
	public AesBytesEncryptor aesBytesEncryptor() {
	    return new AesBytesEncryptor(secret, "70726574657374");
	}
	
	  @Autowired
	  private UserSuccessHandler success;

	  @Autowired
	  private AuthSuccessHandler authsuccess;
	  
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(additionalInfoFilter, UsernamePasswordAuthenticationFilter.class) // 여기에 필터 추가
			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/**")
				.permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("ROLE_M01","ROLE_SUPER")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.successHandler(success)
				.usernameParameter("username")
				.permitAll()
				
			)
			.logout((logout) -> logout.permitAll().deleteCookies("REMEMBER_ME_COOKIE")
			)
			.oauth2Login((oauth2Login) -> oauth2Login
					.loginPage("/login")
					.successHandler(authsuccess)
					.userInfoEndpoint()
					.userService(customOAuthUserService))
			.rememberMe() // remember-me 설정 추가
			.tokenRepository(persistentTokenRepository())
			.rememberMeCookieName("REMEMBER_ME_COOKIE")
            .key(secret) // remember-me 토큰을 생성하기 위한 키 설정
            .tokenValiditySeconds(86400)
            .rememberMeParameter("remember-me");// 토큰 유효 시간 설정 (예: 24시간 = 86400초)
			//.userDetailsService(null)
           
		return http.build();
	}

	
	
	 @Bean
	    public PersistentTokenRepository persistentTokenRepository() {
	        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	        db.setDataSource(dataSource); // 데이터 소스 설정
	        return db;
	    }
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	return (web) -> web.ignoring().antMatchers("/userresources/**");
	}
	
}
