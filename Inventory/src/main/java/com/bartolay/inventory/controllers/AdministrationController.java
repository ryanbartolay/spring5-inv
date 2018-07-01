package com.bartolay.inventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.UserForm;

@Controller
@RequestMapping(value="/administration")
public class AdministrationController {

	@RequestMapping(value="/users")
	public ModelAndView employeesList() {
		ModelAndView mav = new ModelAndView("administration/index");
		mav.addObject("title", "Users");
		mav.addObject("userForm", new UserForm());
		mav.addObject("html", "users/list");
		return mav;
	}
	@RequestMapping(value="/groups")
	public ModelAndView groupList() {
		ModelAndView mav = new ModelAndView("administration/index");
		mav.addObject("html", "groups/list");
		return mav;
	}
	
	@RequestMapping(value="/groups/permission/{group_id}")
	public ModelAndView groupPermission() {
		ModelAndView mav = new ModelAndView("administration/index");
		mav.addObject("html", "groups/editPermission");
		return mav;
	}
}
