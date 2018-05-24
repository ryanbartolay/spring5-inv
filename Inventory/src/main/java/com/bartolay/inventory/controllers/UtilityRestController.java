package com.bartolay.inventory.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@Deprecated
public class UtilityRestController {
	
	@RequestMapping(value="/api/compute/quantityTotal", method=RequestMethod.POST, produces="application/json")
	public String computeForQuantityTotal(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		
		System.err.println(requestMap);
		
		return "";
		//return itemService.retrieveDatatableList(requestMap).toString();
	}

}
