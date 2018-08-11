package com.bartolay.inventory.controllers;

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

import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.exceptions.SupplierException;
import com.bartolay.inventory.form.SupplierForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.SupplierService;
import com.bartolay.inventory.stock.controllers.AbstractRestController;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class SupplierRestController extends AbstractRestController {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping(value = "/api/datatable/suppliers", method = RequestMethod.GET, produces = "application/json")
	public String datatable(@RequestParam Map<String, String> requestMap)
			throws JsonProcessingException, UnsupportedEncodingException {
		return supplierService.findAll(requestMap).toString();
	}
	
	@RequestMapping(value="/api/suppliers", method=RequestMethod.GET, produces="application/json")
	public String suppliersList() throws JsonProcessingException, UnsupportedEncodingException {
		return stringUtils.encode(supplierService.findAll());
	}

	@RequestMapping(value="/api/suppliers/{supplierId}", method=RequestMethod.GET, produces="application/json")
	public String supplierDetail(@PathVariable Integer supplierId) throws JsonProcessingException, UnsupportedEncodingException {
		return stringUtils.encode(supplierService.findById(supplierId));
	}
	
	@RequestMapping(value = "/api/suppliers", method = RequestMethod.POST)
	public String create(@Valid SupplierForm supplierForm, BindingResult bindingResult)
			throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;

		try {
			return supplierService.create(supplierForm).toString();
		} catch (SupplierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}

}
