package com.metflix;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RecommendationsApplication {

	// Rest API を使う場合、この形式で事前登録しておくことが必要。
	@Bean
	protected RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	protected RequestDumperFilter requestDumperFilter() {
		return new RequestDumperFilter();
	}


	public static void main(String[] args) {
		SpringApplication.run(
				RecommendationsApplication.class,
				args);
	}
}
