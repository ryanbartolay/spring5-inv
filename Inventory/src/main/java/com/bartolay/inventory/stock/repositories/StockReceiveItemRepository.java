package com.bartolay.inventory.stock.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.stock.entity.StockReceiveItem;

@Repository
public interface StockReceiveItemRepository extends CrudRepository<StockReceiveItem, Integer> {

}
