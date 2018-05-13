package com.bartolay.inventory.sales.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.CategoryForm;

@Controller
@RequestMapping(value="/sales")
public class SaleController {
	
	@RequestMapping(value="/invoice")
	public ModelAndView invoice() {
		ModelAndView model = new ModelAndView("sales/index");
		model.addObject("page", "Invoice");
		model.addObject("html", "../sales/invoice/list");
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
