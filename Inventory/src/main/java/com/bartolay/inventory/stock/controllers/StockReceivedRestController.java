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

import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.stock.entity.StockReceived;
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
	
	@RequestMapping(value="/api/stock/received", method=RequestMethod.POST)
	public String create(@Valid StockReceivedForm stockReceivedForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;
		try {
			StockReceived stockReceived = stockReceivedService.create(stockReceivedForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully created Document " + stockReceived.getDocumentNumber());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
}
