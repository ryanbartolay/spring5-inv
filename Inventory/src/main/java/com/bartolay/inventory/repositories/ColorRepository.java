package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bartolay.inventory.entity.Color;

public interface ColorRepository extends CrudRepository<Color, Long> {

	List<Color> findAllByOrderByColorAsc();
	List<Color> findAllByOrderByColorDesc();

}
