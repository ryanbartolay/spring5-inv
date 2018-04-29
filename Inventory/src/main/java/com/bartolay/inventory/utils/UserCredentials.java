package com.bartolay.inventory.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.User;

@Component
public class UserCredentials {
	
	/**
	 * Returns logged in user
	 * @return
	 */
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (User) auth.getPrincipal();
	}
	
	/**
	 * Checks if user is logged in to this context
	 * @return
	 */
	public boolean isUserLoggedIn() {
		return SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
				 //when Anonymous Authentication is enabled
				 !(SecurityContextHolder.getContext().getAuthentication() 
				          instanceof AnonymousAuthenticationToken);
	}
}
