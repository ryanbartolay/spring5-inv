package com.bartolay.inventory.sales.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesInvoice;

@Repository
public interface SalesInvoiceRepository extends CrudRepository<SalesInvoice, String> {
	
	@Query(value="SELECT * FROM sales_invoice s ORDER BY system_number ASC LIMIT 1", nativeQuery=true)
	SalesInvoice findOneLimit1();
	
	@Query(value="select si from SalesInvoice as si JOIN FETCH si.customer JOIN FETCH si.location where system_number = :systemNumber")
	SalesInvoice findSalesInvoiceById(@Param("systemNumber") String systemNumber);
	
}
