package com.bartolay.inventory.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.Unit;

@Component
public class InventoryUtility {

	public Inventory findInventoryFromList(List<Inventory> inventories, int location_id, int item_id, int unit_id) {
		return inventories.stream().filter(
				inventory -> 
					inventory.getLocation().getId() == location_id 
				&& 	inventory.getItem().getId() == item_id
				&& 	inventory.getUnit().getId() == unit_id)
				.findAny().orElse(null);
	}

	public Inventory findInventoryFromList(List<Inventory> inventories, Location location, Item item, Unit unit) {
		try {
			return findInventoryFromList(inventories, location.getId(), item.getId(), unit.getId());
		} catch(Exception e) {
			return null;
		}
		
	}
	
}
