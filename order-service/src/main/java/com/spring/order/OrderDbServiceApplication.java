package com.spring.order;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;




@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class OrderDbServiceApplication {
	private static final Logger LOGGER=LoggerFactory.getLogger(OrderDbServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderDbServiceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
