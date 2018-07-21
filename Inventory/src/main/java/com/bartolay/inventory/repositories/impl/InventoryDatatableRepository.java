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
	
	public JSONArray findAllByLocationId(DatatableParameter datatableParameter, Integer location_id) {
		String filter = "";
		String sql = "";
		Object[] params = null;
		if(location_id != null && location_id != 0) {
			filter = "where t1.location_id = ?";
			params = new Object[] { location_id };
			sql = "select t1.*, t2.code as item_code, t2.name as item_name, t3.abbreviation, t3.name as unit_name "
					+ "from inventory as t1 inner join item as t2 on t1.item_id = t2.id " 
					+ "inner join unit as t3 on t1.unit_id = t3.id " + filter
					+ " order by item_name asc";
		}else {
			sql = "select SUM(t1.quantity) as quantity, t1.item_id, t2.code as item_code, t2.name as item_name, t3.abbreviation, t3.name as unit_name " + 
					"from inventory as t1 inner join item as t2 on t1.item_id = t2.id " + 
					"inner join unit as t3 on t1.unit_id = t3.id GROUP BY t2.code,t2.name, t3.abbreviation, t3.name, t1.item_id " + 
					"order by item_name asc";
		}
		
		JSONArray jsonArray = new JSONArray();
		
		List<JSONObject> data = jdbcTemplate.query(sql, params, new RowMapper<JSONObject>() {

			@Override
			public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getInt("id"));
				obj.put("item_id", rs.getInt("item_id"));
				obj.put("item_code", rs.getString("item_code"));
				obj.put("item_name", rs.getString("item_name"));
				obj.put("unit_id", rs.getInt("unit_id"));
				obj.put("unit_name", rs.getString("unit_name"));
				obj.put("on_hand", NumericUtility.quantity(rs.getBigDecimal("quantity")));
				obj.put("on_order", "- -");
				return obj;
			}
		});
		
		data.forEach(d -> jsonArray.put(d));
		
		return jsonArray;
	}
	
	
	public JSONArray findAllDataByLocationId(Integer location_id, Integer limit) {
		final String SQL = "select t1.*, t2.code as item_code, t2.name as item_name, t3.abbreviation, t3.name as unit_name "
					+ "from inventory as t1 inner join item as t2 on t1.item_id = t2.id " 
					+ "inner join unit as t3 on t1.unit_id = t3.id "
					+ "where t1.location_id = ? "
					+ "order by item_name asc limit ?";
		
		JSONArray jsonArray = new JSONArray();
		
		List<JSONObject> data = jdbcTemplate.query(SQL, new Object[] { location_id, limit}, new RowMapper<JSONObject>() {

			@Override
			public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
				JSONObject obj = new JSONObject();
				obj.put("item_id", rs.getInt("item_id"));
				obj.put("item_code", rs.getString("item_code"));
				obj.put("item_name", rs.getString("item_name"));
				obj.put("unit_id", rs.getInt("unit_id"));
				obj.put("unit_name", rs.getString("unit_name"));
				obj.put("on_hand", NumericUtility.quantity(rs.getBigDecimal("quantity")));
				obj.put("on_order", "- -");
				return obj;
			}
		});
		
		data.forEach(d -> jsonArray.put(d));
		
		return jsonArray;
	}

	public JSONArray findQuantityCostByLocationId(Integer location_id) {
		String sql = "select id, item_id, unit_id, quantity, unit_cost from inventory where location_id = ?";
		
		List<JSONObject> data = jdbcTemplate.query(sql,new Object[] {location_id}, new RowMapper<JSONObject>() {

			@Override
			public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getInt("id"));
				obj.put("item_id", rs.getInt("item_id"));
				obj.put("unit_id", rs.getInt("unit_id"));
				obj.put("quantity", rs.getBigDecimal("quantity"));
				obj.put("unit_cost", rs.getBigDecimal("unit_cost"));
				return obj;
			}
			
		});
		JSONArray jsonArray = new JSONArray();
		
		data.forEach(d -> jsonArray.put(d));
		return jsonArray;
	}
	
	
}
