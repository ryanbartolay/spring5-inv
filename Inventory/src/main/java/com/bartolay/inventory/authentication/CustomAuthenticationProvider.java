package com.bartolay.inventory.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Employee;
import com.bartolay.inventory.repositories.EmployeeRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		try {

			System.err.println("is authenticated " + auth.isAuthenticated());
			
			System.out.println("==================== Custom Authentication Provider SUCCESS Start =========================");
			String username = auth.getName();

			Employee employee = employeeRepository.findByUserName(username);

			if(employee != null) {
				System.out.println(auth.getCredentials().toString());
				
				System.out.println("password matches : " + employee.getPassword());
				System.out.println("matches?? " + passwordEncoder.matches(auth.getCredentials().toString(), employee.getPassword()));
				
				if(null != auth.getCredentials() && passwordEncoder.matches(auth.getCredentials().toString(), employee.getPassword())) {
					System.out.println("employee " + employee);

					System.out.println("detailsss  " + auth.getDetails());

					System.out.println("auth " + auth.getPrincipal());
					System.out.println("auth details " + auth);

					System.out.println("username " + username);
					
					
				}
			}

			System.out.println("==================== Custom Authentication Provider SUCCESS END =========================");
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(employee);
			
			return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new BadCredentialsException(ex.getMessage());
		}
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}