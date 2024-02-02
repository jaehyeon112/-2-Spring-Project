package com.bongsamaru;


import org.junit.jupiter.api.Test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bongsamaru.user.mapper.UserMapper;
import com.bongsamaru.common.UserVO;

@SpringBootTest
@MapperScan(basePackages = "com.bongsamaru.**.mapper")
class FinalProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
