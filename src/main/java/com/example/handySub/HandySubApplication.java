package com.example.handySub;

import com.example.handySub.domain.user.collection.UserCollections;
import com.example.handySub.domain.user.collection.UserRole;
import com.example.handySub.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class})
@RequiredArgsConstructor
public class HandySubApplication {

	final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(HandySubApplication.class, args);
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

