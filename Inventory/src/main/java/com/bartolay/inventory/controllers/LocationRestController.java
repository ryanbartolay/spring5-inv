package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.form.LocationForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.LocationService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class LocationRestController {

	@Autowired
	private LocationService locationService;

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private StringUtils stringUtils;

	@RequestMapping(value="/api/datatable/locations", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {
		return locationService.retrieveDatatableList(requestMap).toString();
	}

	@RequestMapping(value="/api/locations/{id}", method=RequestMethod.GET)
	public String getById(@PathVariable Integer id) {
		try {
			Location location = locationService.findOne(id);

			return stringUtils.encode(location);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value="/api/locations", method=RequestMethod.POST)
	public String create(@Valid LocationForm locationForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			Location location = locationService.create(locationForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + location.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}

	@RequestMapping(value="/api/locations", method=RequestMethod.PUT)
	public String update(@Valid LocationForm locationForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			Location location = locationService.update(locationForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + location.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}

	@RequestMapping(value="/api/locations/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) throws RestApiException {
		try {
			Location location = locationService.delete(id);
			ApiResponse response = new ApiResponse(HttpStatus.OK, "Record deleted " + location.getName());
			return stringUtils.encode(response);
		} catch(Exception e) {
			throw new RestApiException(e);
		}
	}
}
