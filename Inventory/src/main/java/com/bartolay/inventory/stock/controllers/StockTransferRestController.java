package com.bartolay.inventory.stock.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.stock.services.StockTransferService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/api")
public class StockTransferRestController {

	@Autowired
	private StockTransferService stockTransferService;
	
	@RequestMapping(value="/datatable/stocks/transfer", method=RequestMethod.GET, produces="application/json")
	public String datatableStockTransfer(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return stockTransferService.retrieveDatatableList(requestMap).toString();
	}
}
