package com.bartolay.inventory.stock.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.stock.entity.StockOpening;

@Repository
public interface StockOpeningRepository extends CrudRepository<StockOpening, Long> {

	@Query(value = "SELECT p FROM Brand p LEFT JOIN FETCH p.company LEFT JOIN FETCH p.createdBy")
    Iterable<StockOpening> apiFindAll();
	
	StockOpening findBySystemNumber(String system_number);
}
