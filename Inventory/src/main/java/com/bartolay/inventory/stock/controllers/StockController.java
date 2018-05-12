package com.bartolay.inventory.stock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.form.StockOpeningForm;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.services.CompanyService;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class StockController {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private StockOpeningRepository stockOpeningRepository;

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="/stock/opening")
	public ModelAndView stockOpeningList() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("page", "Opening Stock");
		model.addObject("html", "opening/list");
		model.addObject("brandForm", new BrandForm());
		model.addObject("companies", companyService.findAll());
		return model;
	}
	
	@RequestMapping(value="/stock/opening/create", method=RequestMethod.GET)
	public ModelAndView stockOpeningCreate(ModelAndView mav) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "New Stock Opening");
		mav.addObject("html", "/opening/edit");
		mav.addObject("method", "POST");
		
		mav.addObject("stockOpeningForm", new StockOpeningForm());
		mav.addObject("locations", locationRepository.findAll());
		return mav;
	}
	
	@RequestMapping(value="/stock/opening/view/{system_number}", method=RequestMethod.GET)
	public ModelAndView stockOpeningView(ModelAndView mav, @PathVariable String system_number) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "Opening Stock");
		mav.addObject("html", "/opening/view");
		StockOpening opening = stockOpeningRepository.findBySystemNumber(system_number);
		System.err.println(opening.getItems());
		mav.addObject("stockOpening", opening);
	
		return mav;
	}
	
	@RequestMapping(value="/stock/opening/update", method=RequestMethod.GET)
	public ModelAndView stockOpeningUpdate(ModelAndView mav) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "New Stock Opening");
		mav.addObject("html", "/opening/edit");
		mav.addObject("method", "POST");
		
		mav.addObject("stockOpeningForm", new StockOpeningForm());
		mav.addObject("locations", locationRepository.findAll());
		return mav;
	}
	
}