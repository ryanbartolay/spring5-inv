package com.bartolay.inventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StockController {

	@RequestMapping(value="/brands")
	public ModelAndView brandsList() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("page", "Brands");
		model.addObject("html", "brands/list");
		return model;
	}
	
}
