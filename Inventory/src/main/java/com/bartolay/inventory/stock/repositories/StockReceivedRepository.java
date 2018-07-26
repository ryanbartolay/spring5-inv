package com.bartolay.inventory.stock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.stock.entity.StockReceived;

@Repository
public interface StockReceivedRepository extends CrudRepository<StockReceived, String> {	
	
	@Query(value="select s from StockReceived s join s.stockReceivedExpenses sre where sre.expense = :expense")
	public List<StockReceived> findAllByExpense(@Param("expense") Expense expense);

}
