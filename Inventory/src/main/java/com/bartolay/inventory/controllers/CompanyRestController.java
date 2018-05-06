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

import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.form.CompanyForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.services.CompanyService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
class CompanyRestController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private StringUtils stringUtils;

	@RequestMapping(value="/api/datatable/companies", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return companyService.retrieveDatatableList(requestMap).toString();
	}

	@RequestMapping(value="/api/companies/{id}", method=RequestMethod.GET)
	public String getById(@PathVariable Long id) {
		try {
			Company company = companyRepository.apiFindById(id);
			return stringUtils.encode(company);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/api/companies", method=RequestMethod.POST)
	public String create(@Valid CompanyForm companyForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			Company company = companyService.create(companyForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + company.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/companies", method=RequestMethod.PUT)
	public String update(@Valid CompanyForm companyForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			Company company = companyService.update(companyForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + company.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/companies/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			Company company = companyService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + company.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}

}
