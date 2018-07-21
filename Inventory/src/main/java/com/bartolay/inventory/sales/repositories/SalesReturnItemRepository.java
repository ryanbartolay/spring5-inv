package com.bartolay.inventory.sales.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesReturnItem;

@Repository
public interface SalesReturnItemRepository extends CrudRepository<SalesReturnItem, Integer> {

}
