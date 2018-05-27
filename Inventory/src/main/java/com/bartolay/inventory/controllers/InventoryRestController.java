package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.repositories.InventoryRepository;

@RestController
@RequestMapping(value="/api/inventory")
public class InventoryRestController {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@RequestMapping(value="/location/{location_id}")
	public String retrieveByLocation(@PathVariable Integer location_id) {
		
		Location location = new Location(location_id);
		return null;
	}
	
}
