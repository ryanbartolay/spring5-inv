package com.bartolay.inventory.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.datatable.model.DatatableColumn;
import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.repositories.DatatableRepository;

@Repository
@Qualifier("clientDataTableRepository")
public class ClientDataTableRepositoryImpl extends RepositoryComponent implements DatatableRepository {
	
	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONArray findAllData(DatatableParameter datatableParameter) {

			DatatableColumn sortColumn = datatableParameter.getSortColumn();
			String SQL = "SELECT c.*, concat_ws(' ', u.firstName, u.lastName) as sales_person from client c inner join users u on c.sales_person_id=u.id";
			List<Object> SQL_PARAMS = new ArrayList<>();
			
			
			if(datatableParameter.getSearch() != null) {
				SQL += " WHERE c.name like ?";
				SQL_PARAMS.add(datatableParameter.getSearch() + "%");
			}

			// sort order by column
			if(sortColumn != null && datatableParameter.getSortOrder() != null) {
				SQL += " ORDER BY "+sortColumn.getData()+ " " + datatableParameter.getSortOrder().name();	
			}

			if(datatableParameter.getLength() > 0) {
				SQL += " LIMIT " + datatableParameter.getLength() + " OFFSET " + datatableParameter.getStart();
			}
			
			List<JSONObject> domains = new ArrayList<>();


			domains = jdbcTemplate.query(SQL, SQL_PARAMS.toArray(), new RowMapper<JSONObject>() {

				@Override
				public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
					JSONObject obj = new JSONObject();
					obj.put("id", rs.getLong("id"));
					obj.put("name", rs.getString("name"));
					obj.put("created_date", rs.getDate("created_date"));
					obj.put("created_by", rs.getString("created_by"));
					obj.put("email", rs.getString("email") != null ? rs.getString("email") : EMPTY);
					obj.put("mobile", rs.getString("phone") != null ? rs.getString("phone") : EMPTY);
					obj.put("address", rs.getString("address") != null ? rs.getString("address") : EMPTY);
					obj.put("address_city", rs.getString("address_city") != null ? rs.getString("address_city") : EMPTY);
					obj.put("address_zipcode", rs.getString("address_zipcode") != null ? rs.getString("address_zipcode") : EMPTY);
					obj.put("sales_person_id", rs.getString("sales_person"));
					return obj;
				}
			});
			
			JSONArray array = new JSONArray();
			
			domains.forEach(domain -> {
				array.put(domain);
			});
			return array;
	}

}
