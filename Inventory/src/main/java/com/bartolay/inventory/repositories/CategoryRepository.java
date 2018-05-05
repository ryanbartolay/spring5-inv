package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}
