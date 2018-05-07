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

import com.bartolay.inventory.entity.Country;
import com.bartolay.inventory.form.CountryForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.CountryService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class CountryRestController {

	@Autowired
	private CountryService countryService;
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/datatable/countries", method=RequestMethod.GET, produces="application/json")
	public String datatable(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return countryService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/api/countries", method=RequestMethod.POST)
	public String create(@Valid CountryForm countryForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}
		ApiResponse response = null;
		
		try {
			Country country = countryService.create(countryForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + country.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/countries", method=RequestMethod.PUT)
	public String update(@Valid CountryForm countryForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			Country country = countryService.update(countryForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + country.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/countries/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			Country country = countryService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + country.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}
	
}
