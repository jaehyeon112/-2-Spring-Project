package com.bongsamaru;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bongsamaru.user.service.UserService;

@SpringBootTest
public class PasswordEncodeTest {
		
	@Autowired
	UserService userService;
	
	
	//@Test
	public void 인서트테스트() {
		String a = "test";
		
	}
	
		//@Test
		public void 암호화() {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
			String result = encoder.encode("1234");
			System.out.println(result);
			assertTrue(encoder.matches("1234", result));
		}
}
