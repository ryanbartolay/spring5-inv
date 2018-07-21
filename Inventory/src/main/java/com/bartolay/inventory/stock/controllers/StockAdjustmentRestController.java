package com.bartolay.inventory.stock.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.form.StockAdjustmentForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.services.StockAdjustmentService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/api")
public class StockAdjustmentRestController extends AbstractRestController {

	@Autowired
	private StockAdjustmentService stockAdjustmentService;
	
	@RequestMapping(value="/datatable/stock/adjustment", method=RequestMethod.GET, produces="application/json")
	public String datatableStockAdjustment(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return stockAdjustmentService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/stock/adjustment", method=RequestMethod.POST)
	public String create(@Valid StockAdjustmentForm stockAdjustmentForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;
		
		try {
			StockAdjustment stockAdjustment = stockAdjustmentService.create(stockAdjustmentForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created Document " + stockAdjustment.getDocumentNumber());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
}
