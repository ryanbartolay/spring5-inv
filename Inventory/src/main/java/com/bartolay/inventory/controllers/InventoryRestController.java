package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	private final static String ZERO = "0";
	
	@RequestMapping(value="/inventory/location", method=RequestMethod.GET, produces="application/json")
	public String getAllByLocation(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {		
		Integer location_id = Integer.parseInt(((requestMap.get("location")!=null && !requestMap.get("location").trim().isEmpty()) ?requestMap.get("location") : ZERO));
		return inventoryService.retrieveDatatableListByLocationId(requestMap, location_id).toString();
	}
	
	@RequestMapping(value="/inventory/location/{location}/limit/{limit}", method=RequestMethod.GET, produces="application/json")
	public String getAllByLocation(@PathVariable Integer location, @PathVariable Integer limit, @RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {		
		return inventoryService.retrieveDatatableListByLocationIdWithLimit(requestMap, location, limit).toString();
	}
	
	@RequestMapping(value="/inventory/quantity_cost_by_location/{location}", method=RequestMethod.GET, produces="application/json")
	public String getQuantityCostByLocation(@PathVariable Integer location, @RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {		
		return inventoryService.retrieveQuantityCostByLocationId(requestMap, location).toString();
	}
}
