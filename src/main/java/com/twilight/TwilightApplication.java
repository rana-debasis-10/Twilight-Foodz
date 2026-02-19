package com.twilight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TwilightApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwilightApplication.class, args);
	}

}
