package com.bartolay.inventory.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.Unit;

@Repository
public class InventoryJDBCRepository extends RepositoryComponent {

	public List<Inventory> findByLocation(Location location) {
		String sql = "select i.id, i.location_id, i.item_id, i.unit_id, "
				+ "l.name as location_name, l.abbreviation as location_abbreviation, "
				+ "it.name as item_name, it.code as item_code, "
				+ "u.name as unit_name, u.abbreviation as unit_abbreviation "
				+ "from "
				+ "inventory i inner join location l on i.location_id = l.id "
				+ "inner join item as it on i.item_id = it.id "
				+ "inner join unit as u on i.unit_id = u.id "
				+ "where i.location_id = ?";

		return jdbcTemplate.query(sql, new Object[] {
				location.getId()
		}, new RowMapper<Inventory>() {

			@Override
			public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Location location = new Location();
				location.setId(rs.getInt("location_id"));
				location.setName(rs.getString("location_name"));
				location.setAbbreviation(rs.getString("location_abbreviation"));
				
				Item item = new Item();
				item.setId(rs.getInt("item_id"));
				item.setName(rs.getString("item_name"));
				item.setCode(rs.getString("item_code"));
				
				Unit unit = new Unit();
				unit.setId(rs.getInt("unit_id"));
				unit.setName(rs.getString("unit_name"));
				unit.setAbbreviation(rs.getString("unit_abbreviation"));
				
				Inventory inventory = new Inventory();
				inventory.setId(rs.getInt("id"));
				inventory.setLocation(location);
				inventory.setItem(item);
				inventory.setUnit(unit);
				return inventory;
			}

		});
	}
}
