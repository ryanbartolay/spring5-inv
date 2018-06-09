package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.services.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/api/datatable")
public class InventoryRestController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value="/inventory/location", method=RequestMethod.GET, produces="application/json")
	public String getAllByLocation(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {		
		Integer location_id = Integer.parseInt(requestMap.get("location"));
		return inventoryService.retrieveDatatableListByLocationId(requestMap, location_id).toString();
	}
}
