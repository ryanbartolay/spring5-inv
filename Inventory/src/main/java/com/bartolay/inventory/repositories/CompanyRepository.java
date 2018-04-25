package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
