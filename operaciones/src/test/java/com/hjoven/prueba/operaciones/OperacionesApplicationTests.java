package com.hjoven.prueba.operaciones;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan(basePackages = {"com.hjoven.prueba.shared.dtos", "com.hjoven.prueba.shared.entities", "com.hjoven.prueba.shared.utils"})
class OperacionesApplicationTests {

	@Test
	void contextLoads() {
	}

}
