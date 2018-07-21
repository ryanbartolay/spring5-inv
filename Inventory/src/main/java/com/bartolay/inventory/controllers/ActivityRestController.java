package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.services.ActivityService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ActivityRestController {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/activities/location/{location_id}", method=RequestMethod.GET, produces="application/json")
	public String getAllByLocation(@PathVariable Integer location_id) throws JsonProcessingException, UnsupportedEncodingException {		
		return stringUtils.encode(activityService.findAllByLocation(new Location(location_id)));
	}
}
