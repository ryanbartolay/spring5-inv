package com.bartolay.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@SpringBootApplication
public class InventoryApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(InventoryApplication.class);
	}
	@Bean
	public RedirectStrategy redirectStrategy() {
		return new DefaultRedirectStrategy();
	}

	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		return new StringHttpMessageConverter();
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter httpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}
}
