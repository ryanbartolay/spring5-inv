package com.bartolay.inventory.stock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.form.StockOpeningForm;
import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.form.StockTransferForm;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.services.CompanyService;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.entity.StockTransfer;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;
import com.bartolay.inventory.stock.repositories.StockReceivedRepository;
import com.bartolay.inventory.stock.repositories.StockTransferRepository;
import com.bartolay.inventory.utils.NumericUtility;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class StockController {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private StockOpeningRepository stockOpeningRepository;
	
	@Autowired
	private StockTransferRepository stockTransferRepository;
	
	@Autowired 
	private StockReceivedRepository stockReceiveRepository;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private NumericUtility numericUtility;
	
	@RequestMapping(value="/stock/opening")
	public ModelAndView stockOpeningList() {
		ModelAndView model = new ModelAndView("stock/index");
		model.addObject("page", "Opening Stock");
		model.addObject("html", "opening/list");
		model.addObject("stockOpeningForm", new StockOpeningForm());
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
	
	@RequestMapping(value="/stock/opening/{system_number}", method=RequestMethod.GET)
	public ModelAndView stockOpeningView(ModelAndView mav, @PathVariable String system_number) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "Opening Stock");
		mav.addObject("html", "/opening/view");
		StockOpening opening = stockOpeningRepository.findBySystemNumber(system_number);
		mav.addObject("stockOpening", opening);
	
		return mav;
	}
	
	@RequestMapping(value="/stock/received")
	public ModelAndView recieve(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Received");
		model.addObject("html", "received/list");
		
		return model;
	}
	
	@RequestMapping(value="/stock/received/{system_number}")
	public ModelAndView recieveView(ModelAndView model, @PathVariable String system_number) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Received");
		model.addObject("html", "received/view");
		model.addObject("numericUtility", numericUtility);
		model.addObject("stockReceive", stockReceiveRepository.findById(system_number).get());
		
		return model;
	}
	
	@RequestMapping(value="/stock/received/create")
	public ModelAndView recieveCreate(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "New Stock Received");
		model.addObject("html", "received/edit");
		model.addObject("stockReceivedForm", new StockReceivedForm());
		
		return model;
	}
	
	@RequestMapping(value="/stock/transfer")
	public ModelAndView stockTransfer(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Transfer");
		model.addObject("html", "/transfer/list");
		model.addObject("stockTransferForm", new StockTransferForm());
		return model;
	}
	
	@RequestMapping(value="/stock/transfer/create", method=RequestMethod.GET)
	public ModelAndView stockTransferCreate(ModelAndView mav) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "New Stock Transfer");
		mav.addObject("html", "/transfer/edit");
		mav.addObject("method", "POST");
		mav.addObject("stockTransferForm", new StockTransferForm());
		mav.addObject("locations", locationRepository.findAll());
		
		return mav;
	}
	
	@RequestMapping(value="/stock/transfer/view/{system_number}", method=RequestMethod.GET)
	public ModelAndView stockTransferView(ModelAndView mav, @PathVariable String system_number) throws JsonProcessingException {
		mav.setViewName("stock/index");
		mav.addObject("page", "Stock Transfer");
		mav.addObject("html", "transfer/view");
		StockTransfer stockTransfer = stockTransferRepository.findById(system_number).get();
		mav.addObject("stockTransfer", stockTransfer);
	
		return mav;
	}
	
	@RequestMapping(value="/stock/adjustment")
	public ModelAndView stockAdjustment(ModelAndView model) {
		model.setViewName("stock/index");
		model.addObject("page", "Stock Adjustment");
		model.addObject("html", "../stock/adjustment/list");
		model.addObject("stockTransferForm", new StockTransferForm());
		return model;
	}
	
}
