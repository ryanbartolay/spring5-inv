package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Model;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {

}
