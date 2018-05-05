package com.bartolay.inventory.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

	@Query(value = "SELECT p FROM Company p LEFT JOIN FETCH p.createdBy where p.id = :id")
    Company apiFindById(@Param("id") Long id);
}
