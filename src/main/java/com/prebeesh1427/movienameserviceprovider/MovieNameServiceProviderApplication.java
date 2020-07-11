package com.prebeesh1427.movienameserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MovieNameServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieNameServiceProviderApplication.class, args);
	}

}
