package com.bartolay.inventory.stock.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.stock.entity.StockAdjustmentReason;

@Repository
public interface StockAdjustmentReasonRepository extends CrudRepository<StockAdjustmentReason, Integer> {
	@Query(value = "SELECT p FROM StockAdjustmentReason p left join fetch p.createdBy")
    Iterable<StockAdjustmentReason> apiFindAll();
}
