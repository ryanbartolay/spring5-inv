package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.services.UserGroupService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserGroupsRestController {
	
	@Autowired
	private UserGroupService userGroupService;

	@RequestMapping(value="/api/datatable/usergroups", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {
		return userGroupService.retrieveDatatableList(requestMap).toString();
	}
}
