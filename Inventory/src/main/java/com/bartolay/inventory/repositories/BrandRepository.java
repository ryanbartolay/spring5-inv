package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {

}
