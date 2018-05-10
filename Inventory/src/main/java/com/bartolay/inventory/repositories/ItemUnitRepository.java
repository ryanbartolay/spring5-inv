package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.ItemUnit;

@Repository
public interface ItemUnitRepository extends CrudRepository<ItemUnit, Long> {

}
