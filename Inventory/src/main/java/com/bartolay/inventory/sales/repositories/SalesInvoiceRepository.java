package com.bartolay.inventory.sales.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesInvoice;

@Repository
public interface SalesInvoiceRepository extends CrudRepository<SalesInvoice, String> {
	
	@Query(value="SELECT * FROM sales_invoice s ORDER BY system_number ASC LIMIT 1", nativeQuery=true)
	SalesInvoice findOneLimit1();
	
}
