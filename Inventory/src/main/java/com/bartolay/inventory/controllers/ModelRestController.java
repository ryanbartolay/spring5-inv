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
import com.bartolay.inventory.entity.Model;
import com.bartolay.inventory.form.CountryForm;
import com.bartolay.inventory.form.ModelForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.ModelService;
import com.bartolay.inventory.stock.controllers.AbstractRestController;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ModelRestController extends AbstractRestController{

	@Autowired
	private ModelService modelService;
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/datatable/models", method=RequestMethod.GET, produces="application/json")
	public String datatable(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return modelService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/api/models", method=RequestMethod.POST)
	public String create(@Valid ModelForm modelForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;
		try {
			Model model = modelService.create(modelForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + model.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/models", method=RequestMethod.PUT)
	public String update(@Valid ModelForm modelForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}

		try {
			Model model = modelService.update(modelForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + model.getName());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/models/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			Model model = modelService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + model.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}
}
