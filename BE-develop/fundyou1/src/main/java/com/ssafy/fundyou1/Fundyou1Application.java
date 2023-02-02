package com.ssafy.fundyou1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Fundyou1Application {

	public static void main(String[] args) {
		SpringApplication.run(Fundyou1Application.class, args);
	}

}
