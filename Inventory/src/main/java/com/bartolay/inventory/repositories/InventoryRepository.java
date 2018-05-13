package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.Location;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer>{

	public List<Inventory> findByLocation(Location location);
	
}
