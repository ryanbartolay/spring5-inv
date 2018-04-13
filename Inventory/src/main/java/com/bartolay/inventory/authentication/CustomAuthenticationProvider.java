package com.bartolay.inventory.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		try {
			System.out.println("====================Custom Authentication Start =========================");
			String username = auth.getName();
			String password = (String) auth.getCredentials();

			System.out.println("detailsss  " + auth.getDetails());

			System.out.println("username " + username);
			System.out.println("password " + password);

			return null;

		} catch(Exception ex) {
			ex.printStackTrace();
			throw new BadCredentialsException(ex.getMessage());
		}
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}