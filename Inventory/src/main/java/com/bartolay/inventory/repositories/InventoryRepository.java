package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.Location;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer>{
	
	@Query(value = "SELECT p FROM Inventory p JOIN FETCH p.item "
			+ "where p.location = :location")
	public List<Inventory> findByLocation(@Param("location") Location location);
}
