package com.bartolay.inventory.sales.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.sales.services.SalesReturnService;
import com.bartolay.inventory.services.ClientService;
import com.bartolay.inventory.stock.controllers.AbstractRestController;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/api")
public class ReturnRestController extends AbstractRestController{

	@Autowired
	private SalesReturnService salesReturnService;
	
	@RequestMapping(value="/datatable/sales/return", method=RequestMethod.GET, produces="application/json")
	public String datatable(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return salesReturnService.retrieveDatatableList(requestMap).toString();
	}
	
}
