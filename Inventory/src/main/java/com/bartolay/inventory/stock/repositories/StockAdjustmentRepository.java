package com.bartolay.inventory.stock.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bartolay.inventory.stock.entity.StockAdjustment;

public interface StockAdjustmentRepository extends CrudRepository<StockAdjustment, String> {

}
