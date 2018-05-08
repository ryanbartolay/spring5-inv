package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.stock.OpeningStock;

@Repository
public interface OpeningStockRepository extends CrudRepository<OpeningStock, Long> {

}
