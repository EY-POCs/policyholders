package com.example.policyholder;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Api(tags = "PolicyHolder")
public class Demo2Application {
	public static void main(String[] args) {
//		System.setProperty("spring.profiles.active", "dev");
		SpringApplication.run(Demo2Application.class, args);
	}
}
