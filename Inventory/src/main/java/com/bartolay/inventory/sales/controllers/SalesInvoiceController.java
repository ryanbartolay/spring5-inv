package com.bartolay.inventory.sales.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.sales.services.SalesInvoiceService;
import com.bartolay.inventory.services.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value="/api")
public class SalesInvoiceController {

	@Autowired
	private SalesInvoiceService salesInvoiceService;
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value="/datatable/sales/invoices", method=RequestMethod.GET, produces="application/json")
	public String datatable(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return salesInvoiceService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/sales/locations", method=RequestMethod.GET, produces="application/json")
	public ObjectNode loadLocations(@RequestParam(name="q", defaultValue="") String query) {
		return locationService.findAllWithPage(query);
	}
}
