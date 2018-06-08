package com.bartolay.inventory.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.utils.NumericUtility;

@Repository
public class InventoryDatatableRepository extends RepositoryComponent {

	public int findAllCountByLocationId(DatatableParameter datatableParameter, Integer location_id) {
		String sql = "select count(id) as total from inventory where location_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {
			location_id
		}, Integer.class);
	}

	public JSONArray findAllDataByLocationId(DatatableParameter datatableParameter, Integer location_id) {
		
		String sql = "select t1.*, t2.code as item_code, t2.name as item_name, t3.abbreviation, t3.name as unit_name "
				+ "from inventory as t1 inner join item as t2 on t1.item_id = t2.id " 
				+ "inner join unit as t3 on t1.unit_id = t3.id " 
				+ "where t1.location_id = ? order by item_name asc";
		
		JSONArray jsonArray = new JSONArray();
		
		List<String[]> data = jdbcTemplate.query(sql, new Object[] { location_id }, new RowMapper<String[]>() {

			@Override
			public String[] mapRow(ResultSet rs, int arg1) throws SQLException {
				String[] data = { rs.getString("item_id") + " : " + rs.getString("item_name"),rs.getString("unit_name"),NumericUtility.quantity(rs.getBigDecimal("quantity")), "- -"};
				
				
				return data;
//				JSONObject obj = new JSONObject();
//				obj.put("item_id", rs.getInt("item_id"));
//				obj.put("item_code", rs.getString("item_code"));
//				obj.put("item_name", rs.getString("item_name"));
//				obj.put("unit_id", rs.getInt("unit_id"));
//				obj.put("unit_name", rs.getString("unit_name"));
//				obj.put("on_hand", NumericUtility.quantity(rs.getBigDecimal("quantity")));
//				obj.put("on_order", "- -");
//				return obj;
			}
		});
		
		data.forEach(d -> jsonArray.put(d));
		
		return jsonArray;
	}
	
	
}
