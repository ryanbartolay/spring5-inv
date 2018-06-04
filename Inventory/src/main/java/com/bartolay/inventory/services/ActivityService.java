package com.bartolay.inventory.services;

import java.util.List;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.sales.entity.Activity;

public interface ActivityService {
	
	public List<Activity> findAll();
	public List<Activity> findAllByLocation(Location location);

}
