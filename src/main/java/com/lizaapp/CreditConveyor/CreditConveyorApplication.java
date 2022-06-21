package com.lizaapp.CreditConveyor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditConveyorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditConveyorApplication.class, args);
	}

	// простое решение, так как это "не тот же самый маппер, что в спринге". Для продвинутых решений - читать гугл.
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
