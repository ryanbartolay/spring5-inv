package com.bartolay.inventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.repositories.ActivityRepository;
import com.bartolay.inventory.sales.entity.Activity;
import com.bartolay.inventory.services.RecentActivityService;

@Service
public class RecentActivityServiceImpl implements RecentActivityService {

	@Autowired
	private ActivityRepository activityRepository;
	
	@Override
	public List<Activity> byLocation(Location location) {
		return activityRepository.findByLocation(location);
	}

}
