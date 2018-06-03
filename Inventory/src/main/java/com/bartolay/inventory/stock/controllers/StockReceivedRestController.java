package com.bartolay.inventory.stock.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.stock.services.StockReceivedService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class StockReceivedRestController extends AbstractRestController {
	
	@Autowired
	private StockReceivedService stockReceivedService;

	@RequestMapping(value="/api/datatable/stockreceived", method=RequestMethod.GET, produces="application/json")
	public String datatableColor(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return stockReceivedService.retrieveDatatableList(requestMap).toString();
	}
}
