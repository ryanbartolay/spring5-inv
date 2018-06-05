package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.sales.entity.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer>{
	
	@Query(value = "SELECT a FROM Activity a JOIN FETCH a.createdBy where a.location = :location")
	public List<Activity> findByLocationOrderByCreatedDateAsc(@Param("location") Location location);

}
