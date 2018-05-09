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

import com.bartolay.inventory.entity.Color;
import com.bartolay.inventory.form.ColorForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.ColorService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ColorRestController {

	@Autowired
	private ColorService colorService;
	@Autowired
	private StringUtils stringUtils;
	@RequestMapping(value="/api/datatable/colors", method=RequestMethod.GET, produces="application/json")
	public String datatableColor(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return colorService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/api/colors", method=RequestMethod.POST)
	public String create(@Valid ColorForm colorForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}
		ApiResponse response = null;
		
		try {
			Color color = colorService.create(colorForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + color.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/colors", method=RequestMethod.PUT)
	public String update(@Valid ColorForm colorForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			Color color = colorService.update(colorForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + color.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/colors/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			Color color = colorService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + color.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}
}
