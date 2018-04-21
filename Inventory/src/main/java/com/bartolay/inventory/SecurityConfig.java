package com.bartolay.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/form/**").permitAll()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/static/**").permitAll()
		.antMatchers("/login").permitAll()
		
		.and()
		.authorizeRequests()
		.antMatchers("/**").authenticated()
		
		.and()
		.formLogin()
		.loginPage("/login")
		
		.and()
		.authenticationProvider(authenticationProvider)
		.logout()
		.invalidateHttpSession(true)
		.permitAll()
		.logoutSuccessUrl("/login?logout");
	}

}
