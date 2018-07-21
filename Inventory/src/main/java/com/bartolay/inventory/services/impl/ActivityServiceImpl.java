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
	public Iterable<Activity> findAll() {
		return activityRepository.findAll();
	}

	@Override
	public List<Activity> findAllByLocation(Location location) {
		List<Activity> acts = activityRepository.findByLocationOrderByCreatedDateAsc(location);
		
		
		
		acts.sort((a, b) -> {
			if(a.getCreatedDate().after(b.getCreatedDate())) {
				System.err.println("ssss");
				return 1;
			} else if(a.getCreatedDate().before(b.getCreatedDate())) {
				System.err.println("aaaaaa");
				return -1;
			}
			System.err.println("dddd");
			return 0;
		});
	
		return acts;
	}
}
