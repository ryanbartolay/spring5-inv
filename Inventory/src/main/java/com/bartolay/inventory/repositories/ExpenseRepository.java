package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;
import com.bartolay.inventory.stock.entity.StockReceivedExpense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer>{

	@Query(value = "SELECT p FROM Expense p left join fetch p.createdBy")
    Iterable<Expense> apiFindAll();
	
}
