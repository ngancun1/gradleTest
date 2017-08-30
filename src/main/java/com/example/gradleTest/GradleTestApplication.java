package com.example.gradleTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class GradleTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradleTestApplication.class, args);
	}
}
