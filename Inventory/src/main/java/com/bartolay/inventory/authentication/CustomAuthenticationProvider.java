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

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.model.Authority;
import com.bartolay.inventory.repositories.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		try {			
			
			String userName = auth.getName();
			String password = (String) auth.getCredentials();
			
			User user = userRepository.findByUsername(userName);
			
			List<Authority> authorities = new ArrayList<>();
			authorities.add(new Authority(user.getAuthority()));
			user.setAuthorities(authorities);
			
			if(passwordEncoder.matches(password, user.getPassword())) {
				return new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
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