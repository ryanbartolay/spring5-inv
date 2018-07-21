package com.bartolay.inventory.stock.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.stock.entity.StockOpeningItem;

@Repository
public interface StockOpeningItemRepository extends CrudRepository<StockOpeningItem, Integer> {

}
