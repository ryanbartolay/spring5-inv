package com.bartolay.inventory.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {

	@Query(value = "SELECT p FROM Brand p LEFT JOIN FETCH p.company LEFT JOIN FETCH p.createdBy")
    Iterable<Brand> apiFindAll();
	
	@Query(value = "SELECT p FROM Brand p LEFT JOIN FETCH p.company LEFT JOIN FETCH p.createdBy where p.id = :id")
    Brand apiFindById(@Param("id") Long id);
}
