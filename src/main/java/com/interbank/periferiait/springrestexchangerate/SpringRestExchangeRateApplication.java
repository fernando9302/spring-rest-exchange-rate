package com.interbank.periferiait.springrestexchangerate;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

import java.util.TimeZone;

@SpringBootApplication
public class SpringRestExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestExchangeRateApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
	}

}
