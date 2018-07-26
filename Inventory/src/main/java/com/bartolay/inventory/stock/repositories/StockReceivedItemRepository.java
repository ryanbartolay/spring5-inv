package com.bartolay.inventory.stock.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.stock.entity.StockReceivedItem;

@Repository
public interface StockReceivedItemRepository extends CrudRepository<StockReceivedItem, Integer> {

}
