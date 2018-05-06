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

import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.form.CategoryForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.CategoryService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/datatable/categories", method=RequestMethod.GET, produces="application/json")
	public String datatable(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return categoryService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/api/categories", method=RequestMethod.POST)
	public String create(@Valid CategoryForm categoryForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}
		ApiResponse response = null;
		
		try {
			Category category = categoryService.create(categoryForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + category.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/categories", method=RequestMethod.PUT)
	public String update(@Valid CategoryForm categoryForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			Category category = categoryService.update(categoryForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + category.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/categories/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			Category category = categoryService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + category.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}
}
