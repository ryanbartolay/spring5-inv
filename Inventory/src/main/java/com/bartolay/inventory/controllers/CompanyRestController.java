package com.bartolay.inventory.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.services.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
class CompanyRestController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="/api/datatable/companies", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		System.err.println("raw request >>>>> " + requestMap);
		return companyService.retrieveDatatableList(requestMap).toString();
	}

}
