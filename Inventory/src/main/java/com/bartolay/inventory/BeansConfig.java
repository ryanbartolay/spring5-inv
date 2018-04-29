package com.bartolay.inventory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Component
public class BeansConfig {

	@Bean
	public JSONObject jsonObject() {
		return new JSONObject();
	}
	
	@Bean
	public JSONArray jsonArray() {
		return new JSONArray();
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