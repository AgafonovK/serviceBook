package com.xvr.serviceBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ServiceBookApplication {

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String pass ="$2a$10$nx/Gyaau2569/Jb6OlB5MuHKl4Om/EcR.ArRDcStJdopp609wlhhK";
		bCryptPasswordEncoder.encode("user");
		System.out.println(bCryptPasswordEncoder.encode("user"));
		SpringApplication.run(ServiceBookApplication.class, args);
	}
}
