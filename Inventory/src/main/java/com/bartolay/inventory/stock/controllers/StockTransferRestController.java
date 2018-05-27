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

import com.bartolay.inventory.form.StockTransferForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.stock.entity.StockTransfer;
import com.bartolay.inventory.stock.services.StockTransferService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/api")
public class StockTransferRestController extends AbstractRestController {

	@Autowired
	private StockTransferService stockTransferService;
	
	@RequestMapping(value="/datatable/stocks/transfer", method=RequestMethod.GET, produces="application/json")
	public String datatableStockTransfer(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return stockTransferService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/stock/transfer", method=RequestMethod.POST)
	public String create(@Valid StockTransferForm openingTransferForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;
		
		try {
			StockTransfer stockTransfer = stockTransferService.create(openingTransferForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created Document " + stockTransfer.getDocumentNumber());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
}
