package com.bartolay.inventory.sales.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesReturnItemReason;

@Repository
public interface SalesReturnItemReasonRepository extends CrudRepository<SalesReturnItemReason, Integer> {

	public SalesReturnItemReason findByCode(String code);
}
