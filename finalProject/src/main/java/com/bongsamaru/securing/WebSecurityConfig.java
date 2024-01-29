package com.bongsamaru.securing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public BCryptPasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/home")
				.permitAll()
				.antMatchers("/admin/**").hasAnyRole("ADMIN","SUPER")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login/FacilityLogin")
				.usernameParameter("username")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll())
			//.userDetailsService(null)
			;

		return http.build();
	}

	
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	return (web) -> web.ignoring().antMatchers("/userresources/**");
	}
	
}
