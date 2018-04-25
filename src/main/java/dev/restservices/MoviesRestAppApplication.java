package dev.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MoviesRestAppApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(MoviesRestAppApplication.class, args);
	}
 
}
