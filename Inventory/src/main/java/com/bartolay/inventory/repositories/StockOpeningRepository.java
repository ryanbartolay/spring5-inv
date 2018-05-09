package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.stock.StockOpening;

@Repository
public interface StockOpeningRepository extends CrudRepository<StockOpening, Long> {

}
