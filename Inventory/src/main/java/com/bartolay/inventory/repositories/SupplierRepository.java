package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer>{
    
}
