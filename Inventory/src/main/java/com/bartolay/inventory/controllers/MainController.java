package com.bartolay.inventory.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.CompanyForm;
import com.bartolay.inventory.services.CompanyService;

@Controller
public class MainController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="/")
	public String getDemo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
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
	
	@RequestMapping(value="/blank")
	public String blank() {
		return "blank";
	}
	
	@RequestMapping(value="/buttons")
	public String buttons() {
		return "buttons";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashboard() {
		return "index";
	}
	
	@RequestMapping(value="/companies", method=RequestMethod.GET)
	public ModelAndView viewList(Model model) {
		ModelAndView mav = new ModelAndView("company/index");
		mav.addObject("companies", companyService.findAll());
		mav.addObject("companyForm", new CompanyForm());
		mav.addObject("html", "list");
		return mav;
	}
	
	@RequestMapping(value="/flot")
	public String flot() {
		return "flot";
	}
	
	@RequestMapping(value="/grid")
	public String grid() {
		return "grid";
	}
	
	@RequestMapping(value="/morris")
	public String morris() {
		return "morris";
	}
	
	@RequestMapping(value="/tables")
	public String tables() {
		return "tables";
	}
	
	@RequestMapping(value="/typography")
	public String typography() {
		return "typography";
	}
	
	@RequestMapping(value="/icons")
	public String icons() {
		return "icons";
	}
	
	@RequestMapping(value="/forms")
	public String forms() {
		return "forms";
	}
	
	@RequestMapping(value="/notifications")
	public String notifications() {
		return "notifications";
	}
	
	@RequestMapping(value="/panels-wells")
	public String panels_wells() {
		return "panels-wells";
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
