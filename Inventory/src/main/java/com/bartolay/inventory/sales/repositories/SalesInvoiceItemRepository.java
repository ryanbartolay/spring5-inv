package com.bartolay.inventory.sales.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;

@Repository
public interface SalesInvoiceItemRepository extends CrudRepository<SalesInvoiceItem, Integer> {

	@Query(value= "select i from SalesInvoiceItem i join fetch i.item join fetch i.unit where i.salesInvoice = :salesInvoice")
	public List<SalesInvoiceItem> findBySalesInvoice(@Param("salesInvoice") SalesInvoice salesInvoice);
}
