package com.bartolay.inventory.sales.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.sales.entity.SalesReturn;

@Repository
public interface SalesReturnRepository extends CrudRepository<SalesReturn, Integer> {
	
	@Query(value="select sr from SalesReturn as sr join fetch sr.salesInvoice left join fetch sr.salesReturnItems where sr.id = :id")
	public SalesReturn findSalesReturnById(@Param("id") Integer id);
	
}
