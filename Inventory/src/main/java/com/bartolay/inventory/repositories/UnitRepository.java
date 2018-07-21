package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Unit;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Integer> {

}
