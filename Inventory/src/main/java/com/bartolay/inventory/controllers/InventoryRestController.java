package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.services.InventoryService;
import com.bartolay.inventory.utils.StringUtils;

@RestController
@RequestMapping(value="/api/inventory")
public class InventoryRestController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private StringUtils stringUtils;
	
}
