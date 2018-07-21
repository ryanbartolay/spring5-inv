package com.bartolay.inventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/report")
public class ReportController {

	@RequestMapping(value="/sales")
	public ModelAndView sales() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}
	
	@RequestMapping(value="/stock")
	public ModelAndView stock() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}
}
