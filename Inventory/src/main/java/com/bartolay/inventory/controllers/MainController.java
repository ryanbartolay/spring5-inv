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

@Controller
public class MainController {
	
	@RequestMapping(value="/")
	public String getDemo() {
		return "index";
	}

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@RequestParam(value = "error", required = false) String error, ModelMap model,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("Authentication " + auth);
		System.out.println(auth.isAuthenticated());
		System.out.println("isAnonymous " + (auth instanceof AnonymousAuthenticationToken));
		
		String client_id = request.getParameter("client_id");
		String redirect_uri = request.getParameter("redirect_uri");
		String response_type = request.getParameter("response_type");
		String state = request.getParameter("state");
		
		
		
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("client_id", client_id);
		model.addAttribute("redirect_uri", redirect_uri);
		model.addAttribute("response_type", response_type);
		model.addAttribute("state", state);		
		
		return "login";

	}
	
	@RequestMapping(value="/dashboard")
	public String dashboard() {
		return "dashboard";
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
