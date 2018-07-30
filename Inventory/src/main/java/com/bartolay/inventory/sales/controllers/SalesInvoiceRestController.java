package com.bartolay.inventory.sales.controllers;

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

import com.bartolay.inventory.exceptions.SalesInvoiceException;
import com.bartolay.inventory.form.SalesInvoiceCancelForm;
import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.services.SalesInvoiceService;
import com.bartolay.inventory.services.LocationService;
import com.bartolay.inventory.stock.controllers.AbstractRestController;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value="/api")
public class SalesInvoiceRestController extends AbstractRestController {

	@Autowired
	private SalesInvoiceService salesInvoiceService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/datatable/sales/invoices", method=RequestMethod.GET, produces="application/json")
	public String datatable(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return salesInvoiceService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/sales/invoice/{systemNumber}/items", method=RequestMethod.GET, produces="application/json")
	public String datatableItems(@PathVariable String systemNumber) throws JsonProcessingException, UnsupportedEncodingException {
		System.out.println(systemNumber);
		return stringUtils.encode(salesInvoiceService.retrieveItemsList(systemNumber));
	}
	
	@RequestMapping(value="/sales/locations", method=RequestMethod.GET, produces="application/json")
	public ObjectNode loadLocations(@RequestParam(name="q", defaultValue="") String query) {
		return locationService.findAllWithPage(query);
	}
	
	@RequestMapping(value="/sales/invoice", method=RequestMethod.POST)
	public String create(@Valid SalesInvoiceForm salesInvoiceForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		System.err.println(salesInvoiceForm);
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			SalesInvoice salesInvoice = salesInvoiceService.create(salesInvoiceForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + salesInvoice.getSystemNumber());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/sales/invoice/cancel", method=RequestMethod.POST)
	public String cancel(@Valid SalesInvoiceCancelForm salesInvoiceCancelForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		
		ApiResponse response = null;
		
		try {	
			System.err.println(salesInvoiceCancelForm);
			SalesInvoice salesInvoice = salesInvoiceService.cancel(salesInvoiceCancelForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully cancelled " + salesInvoice.getSystemNumber());
		} catch(SalesInvoiceException e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/sales/invoices/{systemNumber}", method=RequestMethod.DELETE)
	@Deprecated
	public String create(@PathVariable String systemNumber) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		try {
			//SalesInvoice salesInvoice = salesInvoiceService.cancel(systemNumber);

			//response = new ApiResponse(HttpStatus.OK, "Succesfully cancelled " + salesInvoice.getSystemNumber());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
	@RequestMapping(value="/sales/invoices/all", method=RequestMethod.GET)
	public String allInvoice() throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		return stringUtils.encode(salesInvoiceService.findAll()).toString();
	}

}
