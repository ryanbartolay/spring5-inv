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

import com.bartolay.inventory.entity.Size;
import com.bartolay.inventory.form.SizeForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.SizeService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class SizeRestController {

	@Autowired
	private SizeService sizeService;
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/datatable/sizes", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {
		return sizeService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/api/sizes", method=RequestMethod.POST)
	public String create(@Valid SizeForm sizeForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			Size size = sizeService.create(sizeForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + size.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}

	@RequestMapping(value="/api/sizes", method=RequestMethod.PUT)
	public String update(@Valid SizeForm sizeForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			Size size = sizeService.update(sizeForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + size.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}

	@RequestMapping(value="/api/sizes/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) throws RestApiException {
		try {
			Size size = sizeService.delete(id);
			ApiResponse response = new ApiResponse(HttpStatus.OK, "Record deleted " + size.getName());
			return stringUtils.encode(response);
		} catch(Exception e) {
			throw new RestApiException(e);
		}
	}
}
