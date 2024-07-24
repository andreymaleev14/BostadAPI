package com.homie.Homie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HomieApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomieApplication.class, args);
	}

}
