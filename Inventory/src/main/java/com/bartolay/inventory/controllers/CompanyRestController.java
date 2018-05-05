package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.CompanyService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
class CompanyRestController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private StringUtils stringUtils;

	@RequestMapping(value="/api/datatable/companies", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		System.err.println("raw request >>>>> " + requestMap);
		return companyService.retrieveDatatableList(requestMap).toString();
	}

	@RequestMapping(value="/api/companies/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			Company company = companyService.delete(id);

			response = new ApiResponse(HttpStatus.OK, "Record deleted " + company.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}

}
