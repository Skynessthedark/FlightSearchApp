package com.flightservice.providera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@SpringBootApplication
public class ProvideraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvideraApplication.class, args);
	}

}
