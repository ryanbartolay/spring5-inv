package com.bartolay.inventory.sales.controllers;

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

import com.bartolay.inventory.form.SalesReturnForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.sales.entity.SalesReturn;
import com.bartolay.inventory.sales.services.SalesReturnService;
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
	
	@RequestMapping(value="/sales/return", method=RequestMethod.POST)
	public String create(@Valid SalesReturnForm salesReturnForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		
		try {
			SalesReturn salesReturn = salesReturnService.create(salesReturnForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully cancelled " + salesReturn.getSalesInvoice().getSystemNumber());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	
	
}
