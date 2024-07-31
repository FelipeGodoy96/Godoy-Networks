package com.godoynetworks.Saferoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsSaferoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSaferoomApplication.class, args);
	}

}
