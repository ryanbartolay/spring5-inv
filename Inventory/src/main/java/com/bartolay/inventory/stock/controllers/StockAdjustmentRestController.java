package com.bartolay.inventory.stock.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.stock.services.StockAdjustmentService;
import com.bartolay.inventory.stock.services.StockTransferService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/api")
public class StockAdjustmentRestController {

	@Autowired
	private StockAdjustmentService stockAdjustmentService;
	
	@RequestMapping(value="/datatable/stock/adjustment", method=RequestMethod.GET, produces="application/json")
	public String datatableStockAdjustment(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return stockAdjustmentService.retrieveDatatableList(requestMap).toString();
	}
}
