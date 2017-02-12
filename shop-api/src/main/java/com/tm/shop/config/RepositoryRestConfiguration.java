package com.tm.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryRestConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(
			org.springframework.data.rest.core.config.RepositoryRestConfiguration config) {
		super.configureRepositoryRestConfiguration(config);
		
		config.setDefaultPageSize(5);
	}


	
}
