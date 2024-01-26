package com.bongsamaru;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan(basePackages = "com.bongsamaru.**.mapper")
class FinalProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
