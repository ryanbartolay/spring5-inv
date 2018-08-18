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
			supplier.put("billing_address", rs.getString("billing_address"));
			supplier.put("billing_city", rs.getString("billing_city"));
			supplier.put("billing_zipcode", rs.getString("billing_zipcode"));
			supplier.put("created_date", rs.getDate("created_date"));
			supplier.put("billing_fax", rs.getString("billing_fax"));
			supplier.put("contact_mobile", rs.getString("contact_mobile"));
			supplier.put("company_name", rs.getString("company_name"));
			supplier.put("contact_phone", rs.getString("contact_phone"));
			supplier.put("contact_email", rs.getString("contact_email"));
			supplier.put("updated_date", rs.getDate("updated_date"));
			supplier.put("website", rs.getString("website"));
		
			return supplier;
		}
		
	}

	
}

