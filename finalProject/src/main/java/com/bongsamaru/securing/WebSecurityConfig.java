package com.bongsamaru.securing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
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
	
	  //@Autowired
	  //private UserSuccessHandler success;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/**")
				.permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("ROLE_M01","ROLE_SUPER")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
			//	.successHandler(success)
				.usernameParameter("username")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());
			//.userDetailsService(null)
           
		return http.build();
	}

	
	
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	return (web) -> web.ignoring().antMatchers("/userresources/**");
	}
	
}
