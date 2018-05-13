package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

//	@Query(value="SELECT e FROM Location e WHERE e.name like %?1% ORDER BY e.name ASC ")
    public List<Location> findByNameContaining(String name);
}
	