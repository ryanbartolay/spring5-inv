package com.bartolay.inventory.sales.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.AccountType;
import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.form.SalesReturnForm;
import com.bartolay.inventory.repositories.ClientRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;
import com.bartolay.inventory.services.LocationService;
import com.bartolay.inventory.services.UserService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping(value="/sales")
public class SalesController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;
	
	@Autowired
	private LocationService locationService;
	@Autowired
	private UserService<User> userService;

	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/invoice")
	public ModelAndView invoice(ModelAndView model) {
		model.setViewName("sales/index");
		model.addObject("page", "Invoice");
		model.addObject("salesInvoiceForm", new SalesInvoiceForm());
		model.addObject("locations", locationService.findAll());
		model.addObject("sales", userService.findAllSales(AccountType.SALES));
		model.addObject("html", "invoice/list");
		return model;
	}

	@RequestMapping(value="/customers")
	public ModelAndView customers(ModelAndView model) {
		model.setViewName("sales/index");
		model.addObject("html", "customers/list");
		model.addObject("type", AccountType.USER);
		return model;
	}
	
	@RequestMapping(value="/persons")
	public ModelAndView persons(ModelAndView model) {
		model.setViewName("sales/index");
		model.addObject("html", "persons/list");
		model.addObject("type", AccountType.SALES);
		return model;
	}
	
	@RequestMapping(value="/invoice/create", method=RequestMethod.GET)
	public ModelAndView salesInvoiceCreate(ModelAndView mav) throws JsonProcessingException {
		mav.setViewName("sales/index");
		mav.addObject("page", "New Sales Invoice");
		mav.addObject("html", "invoice/edit");
		mav.addObject("method", "POST");
		
		mav.addObject("users", userService.findAllSales(AccountType.SALES));
		mav.addObject("salesInvoiceForm", new SalesInvoiceForm());
		mav.addObject("locations", locationRepository.findAll());
		mav.addObject("customers", clientRepository.findAll());
		return mav;
	}
	
	@RequestMapping(value="/invoice/{system_number}")
	public ModelAndView invoiceView(ModelAndView model, @PathVariable String system_number) {
		model.setViewName("sales/index");
		model.addObject("page", "Invoice");
		model.addObject("html", "invoice/view");
		
		model.addObject("StringUtils", stringUtils);
		SalesInvoice salesInvoice = salesInvoiceRepository.findById(system_number).get();
		
		model.addObject("salesInvoice", salesInvoice);
		
		return model;
	}
	
	@RequestMapping(value="/return")
	public ModelAndView salesReturn(ModelAndView model) {
		model.setViewName("sales/index");
		model.addObject("html", "return/list");
		return model;
	}
	
	@RequestMapping(value="/return/{system_number}")
	public ModelAndView salesReturnView(ModelAndView mav, @PathVariable String system_number) {
		SalesInvoice salesInvoice = salesInvoiceRepository.findSalesInvoiceById(system_number);
		
		System.err.println("xxxxxxxx");
		System.out.println(salesInvoice.getCustomer());
		
		mav.setViewName("sales/index");
		mav.addObject("html", "return/view");
		mav.addObject("method", "POST");
		
		mav.addObject("salesInvoiceItems", salesInvoice.getSalesInvoiceItems());
		mav.addObject("salesInvoice", salesInvoice);
		return mav;
	}
	
	@RequestMapping(value="/return/create")
	public ModelAndView salesReturnCreate(ModelAndView mav) {
		
		mav.setViewName("sales/index");
		mav.addObject("html", "return/edit");
		mav.addObject("method", "POST");
		
		mav.addObject("salesReturnForm", new SalesReturnForm());
		mav.addObject("salesInvoiceItems", new ArrayList<SalesInvoiceItem>());
		mav.addObject("salesInvoice", new SalesInvoice());
		
		return mav;
	}

	
	@RequestMapping(value="/return/create/{system_number}")
	public ModelAndView salesReturnEdit(ModelAndView mav, @PathVariable String system_number) {
		SalesInvoice salesInvoice = salesInvoiceRepository.findById(system_number).get();
		
		mav.setViewName("sales/index");
		mav.addObject("html", "return/edit");
		mav.addObject("method", "POST");
		
		mav.addObject("salesReturnForm", new SalesReturnForm());
		mav.addObject("salesInvoiceItems", salesInvoice.getSalesInvoiceItems());
		mav.addObject("salesInvoice", salesInvoice);
		return mav;
	}
	

}
