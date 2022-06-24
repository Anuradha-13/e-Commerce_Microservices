package com.spring.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ECommerceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceGatewayApplication.class, args);
	}

}
