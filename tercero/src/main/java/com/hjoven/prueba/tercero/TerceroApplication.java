package com.hjoven.prueba.tercero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.hjoven.prueba.shared.dtos", "com.hjoven.prueba.shared.entities", "com.hjoven.prueba.shared.utils"})
public class TerceroApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerceroApplication.class, args);
	}

}
