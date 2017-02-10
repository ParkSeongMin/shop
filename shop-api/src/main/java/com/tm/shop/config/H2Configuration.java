package com.tm.shop.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * h2 db configuration<br/>
 * h2 console management -&gt; http://localhost:8080/console
 * 
 * @author Park SeongMin
 *
 */
@Component
public class H2Configuration {

/*
spring default configuration

#    jpa:
#        database: H2
#    datasource:
#        url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
#        username: sa
#        password: 
#        driverClassName: org.h2.Driver
*/
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}
	
}
