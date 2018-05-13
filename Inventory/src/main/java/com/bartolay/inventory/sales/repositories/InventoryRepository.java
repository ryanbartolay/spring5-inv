package com.bartolay.inventory.sales.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bartolay.inventory.entity.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Integer>{

}
