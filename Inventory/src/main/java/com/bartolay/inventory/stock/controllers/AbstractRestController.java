package com.bartolay.inventory.stock.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.WebRequest;

import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class AbstractRestController {
	
	@Autowired
	protected StringUtils stringUtils;

	protected String handleRestApiException(final BindingResult bindingResult) {
		
		final List<String> errors = new ArrayList<String>();
		
		for (final FieldError error : bindingResult.getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : bindingResult.getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		//
		final ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST, errors);
//		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
		try {
			return stringUtils.encode(apiError);
		} catch (JsonProcessingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
