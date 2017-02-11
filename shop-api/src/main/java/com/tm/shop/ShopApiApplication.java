package com.tm.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tm.shop.config.RepositoryInitializer;

/**
 * application
 * 
 * @author Park SeongMin
 *
 */
@SpringBootApplication
public class ShopApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShopApiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(RepositoryInitializer initializer) {
		return (args) -> {
			initializer.initialize();
		};
	}
}
