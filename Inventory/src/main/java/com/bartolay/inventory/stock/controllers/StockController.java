package com.bartolay.inventory.stock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class StockController {

	@RequestMapping(value="/stock/opening/create", method=RequestMethod.GET)
	public ModelAndView datatableColor(ModelAndView mav) throws JsonProcessingException {
		System.err.println(mav);
		return null;
		
	}
	
	
}
