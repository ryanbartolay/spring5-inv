package com.bartolay.inventory.stock.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;

@Repository
public interface StockAdjustmentRepository extends CrudRepository<StockAdjustment, String> {
	public List<StockAdjustment> findAllByStockAdjustmentReason(StockAdjustmentReason reason);
}
