package com.bartolay.inventory.stock.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.stock.entity.StockAdjustmentReason;

@Repository
public interface StockAdjustmentReasonRepository extends CrudRepository<StockAdjustmentReason, Integer> {

}
