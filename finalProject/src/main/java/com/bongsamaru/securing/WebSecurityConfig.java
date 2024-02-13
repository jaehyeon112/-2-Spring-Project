package com.bongsamaru.securing;

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
			.logout((logout) -> logout.permitAll()
			)
			.oauth2Login((oauth2Login) -> oauth2Login
					.loginPage("/login")
					.successHandler(authsuccess)
					.userInfoEndpoint()
					.userService(customOAuthUserService));
			//.userDetailsService(null)
           
		return http.build();
	}

	
	
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	return (web) -> web.ignoring().antMatchers("/userresources/**");
	}
	
}
