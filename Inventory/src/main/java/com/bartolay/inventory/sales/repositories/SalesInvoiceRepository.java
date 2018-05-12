package com.bartolay.inventory.sales.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesInvoice;

@Repository
public interface SalesInvoiceRepository extends CrudRepository<SalesInvoice, String> {

}
