package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.exceptions.CurrencyException;
import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.form.CurrencyForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.CurrencyService;
import com.bartolay.inventory.stock.controllers.AbstractRestController;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class SettingsRestController extends AbstractRestController {

	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private StringUtils stringUtils;

	@RequestMapping(value = "/api/datatable/settings/currencies", method = RequestMethod.GET, produces = "application/json")
	public String retrieveAll(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {
		return currencyService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value = "/api/currencies/{currencyId}", method = RequestMethod.GET, produces = "application/json")
	public String currencyDetail(@PathVariable Integer currencyId)
			throws JsonProcessingException, UnsupportedEncodingException {
		return stringUtils.encode(currencyService.findById(currencyId));
	}

	@RequestMapping(value = "/api/currencies", method = RequestMethod.POST)
	public String createCurrency(@Valid CurrencyForm currencyForm, BindingResult bindingResult)
			throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;

		try {
			if(currencyForm.getCurrency_id() != null) {
				return currencyService.update(currencyForm).toString();
			} else {
				return currencyService.create(currencyForm).toString();
			}
		} catch (CurrencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/currencies/{id}", method=RequestMethod.DELETE, produces="application/json")
	public String deleteCurrencies(@PathVariable("id") Integer id) throws StockAdjustmentException, JsonProcessingException, UnsupportedEncodingException {

		StockAdjustmentReason reason = new StockAdjustmentReason();
		reason.setId(id);
		
		ApiResponse response = null;
		
		try {
			JSONObject obj = currencyService.delete(id);
			response = new ApiResponse(HttpStatus.OK, obj.get("localizedMessage").toString()); 
		} catch(CurrencyException e) {			
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage(), e.getErrors());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
		
	}

}
