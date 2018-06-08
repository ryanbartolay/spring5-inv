package com.bartolay.inventory.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class InventoryController {
			
	@RequestMapping(value="/inventory", method=RequestMethod.GET)
	public ModelAndView inventory() {
		ModelAndView model = new ModelAndView("inventory/index");
		model.addObject("page", "Inventories");
		model.addObject("html", "list");
		return model;
	}
}
