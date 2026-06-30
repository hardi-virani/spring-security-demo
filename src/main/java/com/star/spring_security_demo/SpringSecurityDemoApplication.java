package com.star.spring_security_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		System.out.println("encoded pass" + new BCryptPasswordEncoder().encode("1@2")); //not a good idea for production.
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

}
