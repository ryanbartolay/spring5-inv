package com.bartolay.inventory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
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
		.successHandler(authenticationSuccessHandler)
		.failureHandler(authenticationFailureHandler)
		
		.and()
		.authenticationProvider(authenticationProvider)
		.logout()
		.invalidateHttpSession(true)
		.permitAll()
		.logoutSuccessUrl("/login?logout");
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//        .usersByUsernameQuery("select username, password, enabled"
//            + " from employees where username=?")
//        .authoritiesByUsernameQuery("select username, authority "
//            + "from employees where username=?")
//        .passwordEncoder(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder());
		
		auth.authenticationProvider(authenticationProvider);
	}

}
