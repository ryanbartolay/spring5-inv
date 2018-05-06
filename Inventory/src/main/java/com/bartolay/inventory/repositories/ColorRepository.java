package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bartolay.inventory.entity.Color;

public interface ColorRepository extends CrudRepository<Color, Long> {
	
}
