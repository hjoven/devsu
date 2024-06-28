package com.hjoven.prueba.operaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.hjoven.prueba.shared.dtos", "com.hjoven.prueba.shared.entities", "com.hjoven.prueba.shared.utils"})
public class OperacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperacionesApplication.class, args);
	}

}
