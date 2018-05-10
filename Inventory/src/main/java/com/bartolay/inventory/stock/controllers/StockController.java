package com.bartolay.inventory.stock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.StockOpeningForm;
import com.bartolay.inventory.repositories.LocationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class StockController {
	
	@Autowired
	private LocationRepository locationRepository;

	@RequestMapping(value="/stock/opening/create", method=RequestMethod.GET)
	public ModelAndView datatableColor(ModelAndView mav) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "New Stock Opening");
		mav.addObject("html", "/opening/edit");
		mav.addObject("method", "POST");
		
		mav.addObject("stockOpeningForm", new StockOpeningForm());
		mav.addObject("locations", locationRepository.findAll());
		return mav;
	}
	
}
