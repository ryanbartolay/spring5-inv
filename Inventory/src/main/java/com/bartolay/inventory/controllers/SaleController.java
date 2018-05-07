package com.bartolay.inventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/sales")
public class SaleController {
	
	@RequestMapping(value="/invoice")
	public ModelAndView invoice() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}
	
	@RequestMapping(value="/recieve")
	public ModelAndView recieve() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}
	
	@RequestMapping(value="/return")
	public ModelAndView salesReturn() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}

}
