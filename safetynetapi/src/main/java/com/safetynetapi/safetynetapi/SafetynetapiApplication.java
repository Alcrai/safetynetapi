package com.safetynetapi.safetynetapi;

import com.safetynetapi.repository.ILoadData;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.repository.LoadData;
import com.safetynetapi.repository.LoadingDataJson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.safetynetapi.*")
public class SafetynetapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetapiApplication.class, args);
	}
	
}
