package com.bank.activeWithdraw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ActiveWithdrawApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveWithdrawApplication.class, args);
	}

}
