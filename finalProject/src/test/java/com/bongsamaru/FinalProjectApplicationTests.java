package com.bongsamaru;


import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bongsamaru.admin.mapper.UserMapper;
import com.bongsamaru.admin.service.UserVO;

@SpringBootTest
@MapperScan(basePackages = "com.bongsamaru.**.mapper")
class FinalProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
