package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.InventoryTransaction;
import com.bartolay.inventory.enums.TransactionType;

@Repository
public interface InventoryTransactionRepository extends CrudRepository<InventoryTransaction, Integer>{

	public List<InventoryTransaction> findByTransactionSystemNumberAndTransactionType(String transactionSystemNumber, TransactionType transactionType);
}
