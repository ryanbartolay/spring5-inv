package com.bartolay.inventory.sales.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.form.CategoryForm;
import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.services.LocationService;
import com.bartolay.inventory.services.UserService;

@Controller
@RequestMapping(value="/sales")
public class SaleController {
	
	@Autowired
	private LocationService locationService;
	@Autowired
	private UserService<User> userService;
	
	@RequestMapping(value="/invoice")
	public ModelAndView invoice() {
		ModelAndView model = new ModelAndView("sales/index");
		model.addObject("page", "Invoice");
		model.addObject("salesInvoiceForm", new SalesInvoiceForm());
		model.addObject("locations", locationService.findAll());
		model.addObject("sales", userService.findAllSales());
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
