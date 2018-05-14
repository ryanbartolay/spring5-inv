package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.InventoryTransaction;

@Repository
public interface InventoryTransactionRepository extends CrudRepository<InventoryTransaction, Integer>{

}
