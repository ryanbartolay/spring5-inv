package com.bartolay.inventory.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.datatable.model.DatatableParameter;

@Repository
public class SupplierJdbcRepository extends RepositoryComponent implements DatatableRepository {

	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONArray findAllData(DatatableParameter datatableParameter) {
		String sql = "select * from supplier";
		List<JSONObject> suppliers = jdbcTemplate.query(sql, new SupplierRowMapper());
		
		JSONArray array = new JSONArray();
		
		suppliers.forEach(supplier -> {
			array.put(supplier);
		});
		return array;
	}
	
	class SupplierRowMapper implements RowMapper<JSONObject> {

		@Override
		public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
			JSONObject supplier = new JSONObject();
			
			supplier.put("id", rs.getInt("id"));
			supplier.put("address", rs.getString("address"));
			supplier.put("address_city", rs.getString("address_city"));
			supplier.put("address_zipcode", rs.getInt("address_zipcode"));
			supplier.put("created_date", rs.getDate("created_date"));
			supplier.put("fax", rs.getString("fax"));
			supplier.put("mobile", rs.getString("mobile"));
			supplier.put("name", rs.getString("name"));
			supplier.put("phone", rs.getString("phone"));
			supplier.put("updated_date", rs.getDate("updated_date"));
			supplier.put("website", rs.getString("website"));
		
			return supplier;
		}
		
	}

	
}
