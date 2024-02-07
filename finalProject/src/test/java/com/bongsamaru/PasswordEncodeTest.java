package com.bongsamaru;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bongsamaru.common.VO.SubCodeVO;
import com.bongsamaru.common.service.CommonService;
import com.bongsamaru.user.service.UserService;

@SpringBootTest
public class PasswordEncodeTest {
		
	@Autowired
	UserService userService;
	
	@Autowired
	CommonService commonService;
	
	@Test
	public void 결과값보기() {
	    String main = "p";
	    List<SubCodeVO> list = commonService.selectSubCode(main);

	    // 결과 리스트 출력
	    System.out.println(list);
	}
		//@Test
		public void 암호화() {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
			String result = encoder.encode("1234");
			System.out.println(result);
			assertTrue(encoder.matches("1234", result));
		}
}
