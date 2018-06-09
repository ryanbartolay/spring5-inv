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

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.form.UnitForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.UnitService;
import com.bartolay.inventory.stock.controllers.AbstractRestController;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UnitRestController extends AbstractRestController{


	@Autowired
	private UnitService unitService;

	@Autowired
	private StringUtils stringUtils;

	@RequestMapping(value="/api/datatable/units", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {
		return unitService.retrieveDatatableList(requestMap).toString();
	}

	@RequestMapping(value="/api/units/{id}", method=RequestMethod.GET)
	public String getById(@PathVariable Long id) {
		try {
			Brand brand = null;

			return stringUtils.encode(brand);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value="/api/units", method=RequestMethod.POST)
	public String create(@Valid UnitForm unitForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			Unit unit = unitService.create(unitForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + unit.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}

	@RequestMapping(value="/api/units", method=RequestMethod.PUT)
	public String update(@Valid UnitForm unitForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			Unit unit = unitService.update(unitForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + unit.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}

	@RequestMapping(value="/api/units/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		System.err.println("id " + id);
		try {
			Unit unit = unitService.delete(id);
			ApiResponse response = new ApiResponse(HttpStatus.OK, "Record deleted " + unit.getName());
			return stringUtils.encode(response);
		} catch(Exception e) {
			return stringUtils.encode(new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
	}
}
