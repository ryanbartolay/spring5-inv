package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.services.ActivityService;

@Controller
@RequestMapping(value="/fragments")
public class FragmentsController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value="/activity/location/{location_id}")
	public ModelAndView recentActivity(@PathVariable Integer location_id) {
		ModelAndView mav = new ModelAndView("fragments/recentActivity :: recentActivity");
		
		mav.addObject("activities", activityService.findAllByLocation(new Location(location_id)));
		mav.addObject("demoText", "helloWORLDDD");
		return mav;
	}
}
