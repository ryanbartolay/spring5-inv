package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.ItemUnit;
import com.bartolay.inventory.entity.Unit;

@Repository
public interface ItemUnitRepository extends CrudRepository<ItemUnit, Long> {

	List<ItemUnit> findByItem(Item item);
	
	ItemUnit findByItemAndUnit(Item item, Unit unit);
}
