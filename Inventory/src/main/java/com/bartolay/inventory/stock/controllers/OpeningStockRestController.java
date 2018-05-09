package com.bartolay.inventory.stock.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.form.OpeningStockForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.services.StockOpeningService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class OpeningStockRestController {

	@Autowired
	private StockOpeningService openingStockService;
	
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/datatable/openingstock", method=RequestMethod.GET, produces="application/json")
	public String datatableColor(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return openingStockService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/api/openingstock", method=RequestMethod.POST)
	public String create(@Valid OpeningStockForm openingStockForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}
		ApiResponse response = null;
		
		try {
			StockOpening openingStock = openingStockService.create(openingStockForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created Document " + openingStock.getDocumentNumber());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/openingstock", method=RequestMethod.PUT)
	public String update(@Valid OpeningStockForm openingStockForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			StockOpening openingStock = openingStockService.update(openingStockForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated Document " + openingStock.getDocumentNumber());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/openingstock/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			StockOpening openingStock = openingStockService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + openingStock.getDocumentNumber());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}
}
