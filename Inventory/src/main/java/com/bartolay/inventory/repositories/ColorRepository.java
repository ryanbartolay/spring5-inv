package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bartolay.inventory.entity.Color;

public interface ColorRepository extends CrudRepository<Color, Integer> {
	
	public Color findByName(String name);
	
}
