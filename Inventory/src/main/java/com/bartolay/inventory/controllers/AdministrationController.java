package com.bartolay.inventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.UserForm;

@Controller
public class AdministrationController {

	@RequestMapping(value="/users")
	public ModelAndView employeesList() {
		ModelAndView mav = new ModelAndView("generic");
		mav.addObject("title", "Users");
		mav.addObject("userForm", new UserForm());
		mav.addObject("html", "/administration/users/list");
		return mav;
	}
}
