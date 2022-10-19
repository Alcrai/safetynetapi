package com.safetynetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("com.safetynetapi.*")
public class SafetynetapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetapiApplication.class, args);
	}


}
