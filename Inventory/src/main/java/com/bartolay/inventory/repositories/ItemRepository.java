package com.bartolay.inventory.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
	
	@Query(value = "SELECT p FROM Item p LEFT JOIN FETCH p.brand "
			+ "LEFT JOIN FETCH p.category "
			+ "LEFT JOIN FETCH p.color "
			+ "LEFT JOIN FETCH p.size "
			+ "LEFT JOIN FETCH p.model "
			+ "LEFT JOIN FETCH p.defaultUnit "
			+ "LEFT JOIN FETCH p.category "
			+ "LEFT JOIN FETCH p.country "
			+ "LEFT JOIN FETCH p.supplier "
			+ "where p.id = :id")
    public Item apiFindById(@Param("id") Integer id);
}
