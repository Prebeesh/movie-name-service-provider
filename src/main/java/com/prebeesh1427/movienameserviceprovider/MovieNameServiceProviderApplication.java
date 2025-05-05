package com.prebeesh1427.movienameserviceprovider;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@OpenAPIDefinition(
    info = @Info(
        title = "Movie Name Service Provider API",
        version = "1.0",
        description = "APIs for searching movies and their streaming availability by country."
    )
)
@SpringBootApplication
@EnableCaching
public class MovieNameServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieNameServiceProviderApplication.class, args);
	}

}
