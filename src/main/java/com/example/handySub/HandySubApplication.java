package com.example.handySub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class HandySubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandySubApplication.class, args);
	}

}
