package com.bartolay.inventory;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// Within Spring Web MVC, the first step is to ensure that we have a controller that can point to our view. 
// Since our project adds the javaconfig/messages project as a dependency and it contains a view controller 
// for /login we do not need to create a controller within our application
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {  
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();  
		resolver.setPrefix("/templates/");  
		resolver.setSuffix(".html");
		resolver.setCache(false);
		return resolver;  
	}
}
