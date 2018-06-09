package com.bartolay.inventory.sales.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.services.ClientService;
import com.bartolay.inventory.stock.controllers.AbstractRestController;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/api")
public class CustomerRestController extends AbstractRestController{

	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/datatable/sales/customers", method=RequestMethod.GET, produces="application/json")
	public String datatable(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return clientService.retrieveDatatableList(requestMap).toString();
	}
	
}
