package com.bartolay.inventory.sales.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesReturn;

@Repository
public interface SalesReturnRepository extends CrudRepository<SalesReturn, String> {
	
}
