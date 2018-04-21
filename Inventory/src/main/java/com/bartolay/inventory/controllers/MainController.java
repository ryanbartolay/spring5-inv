package com.bartolay.inventory.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.entity.Employee;

@Controller
public class MainController {

	@RequestMapping(value="/")
	public String getDemo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.err.println(((Employee) auth.getPrincipal()).getLastName());
		System.err.println(auth.getAuthorities());
		return "index";
	}

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@RequestParam(value = "error", required = false) String error, ModelMap model,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		// Redirect if already logged in
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/dashboard";
		}
		
		return "login";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashboard() {
		return "index";
	}
	
	@RequestMapping(value="/tables")
	public String tables() {
		return "tables";
	}
	
	@RequestMapping(value = "/403", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				model.addObject("username", userDetail.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("403");
		return model;

	}
}
