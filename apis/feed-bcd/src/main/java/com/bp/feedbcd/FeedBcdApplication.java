package com.bp.feedbcd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeedBcdApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedBcdApplication.class, args);
	}

}
