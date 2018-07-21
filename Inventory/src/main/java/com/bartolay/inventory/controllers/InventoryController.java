package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.services.LocationService;

@RestController
public class InventoryController {
			
	@Autowired
	private LocationService locationService;

	@RequestMapping(value="/inventory", method=RequestMethod.GET)
	public ModelAndView inventory() {
		ModelAndView model = new ModelAndView("inventory/index");
		model.addObject("page", "Inventories");
		model.addObject("html", "list");
		model.addObject("locations", locationService.findAll());
		return model;
	}
}
