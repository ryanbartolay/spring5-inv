package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
