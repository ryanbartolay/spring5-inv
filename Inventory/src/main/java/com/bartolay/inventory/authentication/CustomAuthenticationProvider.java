package com.bartolay.inventory.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Employee;
import com.bartolay.inventory.model.Authority;
import com.bartolay.inventory.repositories.EmployeeRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		try {			
			
			String userName = auth.getName();
			String password = (String) auth.getCredentials();
			
			Employee employee = employeeRepository.findByUserName(userName);
			System.err.println("EMPLOYYESS  " + employee);
			
			List<Authority> authorities = new ArrayList<>();
			authorities.add(new Authority(employee.getAuthority()));
			
			if(passwordEncoder.matches(password, employee.getPassword())) {
				return new UsernamePasswordAuthenticationToken(employee, employee.getPassword(), authorities);
			}
			
			throw new BadCredentialsException("Invalid Username and Password");
			
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new BadCredentialsException(ex.getMessage());
		}
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}