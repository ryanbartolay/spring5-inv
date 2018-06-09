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
	
	@RequestMapping(value="/inventory/location/{location_id}", method=RequestMethod.GET, produces="application/json")
	public String getAllByLocation(@RequestParam Map<String, String> requestMap, @PathVariable Integer location_id) throws JsonProcessingException, UnsupportedEncodingException {		
		return inventoryService.retrieveDatatableListByLocationId(requestMap, location_id).toString();
	}
}
