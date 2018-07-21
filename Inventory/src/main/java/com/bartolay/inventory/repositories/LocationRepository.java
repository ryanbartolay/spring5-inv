package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

	List<Location> findByNameContaining(String query);

}
