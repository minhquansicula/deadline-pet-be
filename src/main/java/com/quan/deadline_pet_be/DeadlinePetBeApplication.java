package com.quan.deadline_pet_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DeadlinePetBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeadlinePetBeApplication.class, args);
	}

}
