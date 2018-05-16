package com.bartolay.inventory.sales.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesInvoiceItem;

@Repository
public interface SalesInvoiceItemRepository extends CrudRepository<SalesInvoiceItem, Integer> {
	
	public List<SalesInvoiceItem> findBySystemNumber(String systemNumber);
}
