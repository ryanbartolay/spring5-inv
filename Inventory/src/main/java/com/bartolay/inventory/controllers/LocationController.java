package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.LocationForm;
import com.bartolay.inventory.repositories.LocationRepository;

@Controller
public class LocationController {
	
	@Autowired
	private LocationRepository locationRepository;

	@RequestMapping(value="/locations")
	public ModelAndView locations() {
		ModelAndView model = new ModelAndView("locations/index");
		model.addObject("page", "Locations");
		model.addObject("locationForm", new LocationForm());
		model.addObject("html", "list");
		return model;
	}
	
	@RequestMapping(value="/locations/{location_id}")
	public ModelAndView viewLocation(@PathVariable Integer location_id) {
		ModelAndView model = new ModelAndView("locations/index");
		model.addObject("location", locationRepository.findById(location_id).get());
		model.addObject("html", "view");
		model.addObject("  ", location_id);
		return model;
	}
}
