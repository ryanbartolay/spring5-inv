package com.bartolay.inventory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.repositories.ActivityRepository;
import com.bartolay.inventory.sales.entity.Activity;
import com.bartolay.inventory.services.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Override
	public List<Activity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> findAllByLocation(Location location) {
		return activityRepository.findByLocation(location);
	}
	
	

}
