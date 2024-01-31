package com.bongsamaru;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bongsamaru.dona.mapper.DonaMapper;
import com.bongsamaru.dona.service.DonaVO;

@SpringBootTest
//@MapperScan(basePackages = "com.bongsamaru.**.mapper")
class FinalProjectApplicationTests {
	
	@Autowired
	DonaMapper donaMapper;
	
	

   
   }
   
