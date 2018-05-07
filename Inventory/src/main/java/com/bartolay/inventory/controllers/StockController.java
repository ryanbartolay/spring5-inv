package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.form.CategoryForm;
import com.bartolay.inventory.form.ColorForm;
import com.bartolay.inventory.form.CountryForm;
import com.bartolay.inventory.form.ModelForm;
import com.bartolay.inventory.services.BrandService;
import com.bartolay.inventory.services.CompanyService;

@Controller
public class StockController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private BrandService brandService;
	
	@RequestMapping(value="/brands")
	public ModelAndView brandsList() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("page", "Brands");
		model.addObject("html", "brands/list");
		model.addObject("brandForm", new BrandForm());
		model.addObject("companies", companyService.findAll());
		return model;
	}
		
	@RequestMapping(value="/categories")
	public ModelAndView categories() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("page", "Categories");
		model.addObject("html", "categories/list");
		model.addObject("categoryForm", new CategoryForm());
		model.addObject("companies", companyService.findAll());
		return model;
	}
	
	@RequestMapping(value="/colors")
	public ModelAndView colors() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("page", "Colors");
		model.addObject("colorForm", new ColorForm());
		model.addObject("html", "colors/list");
		return model;
	}
	
	@RequestMapping(value="/countries")
	public ModelAndView countries() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("page", "Countries");
		model.addObject("countryForm", new CountryForm());
		model.addObject("html", "countries/list");
		return model;
	}
	
	@RequestMapping(value="/models")
	public ModelAndView modelsList() {
		ModelAndView model = new ModelAndView("stock/index");
//		ModelAndView model = new ModelAndView("construction");
		model.addObject("page", "Models");
		model.addObject("modelForm", new ModelForm());
		model.addObject("brands", brandService.findAll());
		model.addObject("html", "models/list");
		return model;
	}

	@RequestMapping(value="/unit")
	public ModelAndView unit() {
		ModelAndView model = new ModelAndView("construction");
		return model;
	}	
}
