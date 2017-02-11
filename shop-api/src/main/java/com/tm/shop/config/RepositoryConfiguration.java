package com.tm.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tm.shop.repository.event.PhotoEventHandler;
import com.tm.shop.repository.event.ShopEventHandler;

@Configuration
public class RepositoryConfiguration {

	@Bean
	public ShopEventHandler shopEventHandler() {
		return new ShopEventHandler();
	}
	
	@Bean
	public PhotoEventHandler photoEventHandler() {
		return new PhotoEventHandler();
	}
	
}
