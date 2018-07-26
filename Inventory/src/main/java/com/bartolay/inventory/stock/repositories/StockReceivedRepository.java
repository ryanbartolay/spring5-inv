package com.bartolay.inventory.stock.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.entity.StockReceivedExpense;

@Repository
public interface StockReceivedRepository extends CrudRepository<StockReceived, String> {	
}
