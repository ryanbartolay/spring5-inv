package com.bartolay.inventory.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.services.EmployeeService;

@Controller
@RequestMapping(value="/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService<User> employeeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView viewUsers(Model model) {
		//model.addAttribute("users", userService.getAll());
		ModelAndView mav = new ModelAndView("user/list");
		mav.addObject("users", employeeService.findAll());
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createForm(Model model) {
		ModelAndView mav = new ModelAndView("user/edit");
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult, Model model) {
		System.out.println("create user : " + user);
		
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.setViewName("user/edit");
			return mav;
		}
		
		try {
			employeeService.create(user);	
		} catch(Exception e) {
			bindingResult.reject("error.app.binding.webform", "Error!!!");
		}
		
		mav.setViewName("user/edit_profile");
		return mav;
	}
	
	
	@RequestMapping(value="/edit/{username}", method=RequestMethod.GET)
	public ModelAndView create(Model model, @PathVariable String username) {
		//userService.
		//model.addAttribute("users", userService.getAll());
		ModelAndView mav = new ModelAndView("user/edit");
		return mav;
	}

	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String viewUserByUsername(ModelMap map, @PathVariable("username") String username) {
		return "users/view_single";
	}
}
