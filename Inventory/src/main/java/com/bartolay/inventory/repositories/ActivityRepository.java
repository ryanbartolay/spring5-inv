package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.sales.entity.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer>{
	
	public List<Activity> findByLocation(Location location);

}
