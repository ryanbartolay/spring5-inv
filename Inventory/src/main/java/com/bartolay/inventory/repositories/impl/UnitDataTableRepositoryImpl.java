package com.bartolay.inventory.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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
@Qualifier("unitDatatableRepository")
public class UnitDataTableRepositoryImpl extends RepositoryComponent implements DatatableRepository {
	
	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		String SQL = "SELECT COUNT(u.id) FROM unit u ";

		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE u.name like :name ";
		}

		Query query = em.createNativeQuery(SQL);
		if(datatableParameter.getSearch() != null) {
			query.setParameter("name", PERCENT + datatableParameter.getSearch() + PERCENT);
		}
		return Long.parseLong(query.getSingleResult().toString());
	}

	@Override
	public JSONArray findAllData(DatatableParameter datatableParameter) {

		try{
			DatatableColumn sortColumn = datatableParameter.getSortColumn();
			String SQL = "SELECT * FROM unit b ";
			List<Object> SQL_PARAMS = new ArrayList<>();
			
			
			if(datatableParameter.getSearch() != null) {
				SQL += " WHERE b.name like ?";
				SQL_PARAMS.add(datatableParameter.getSearch() + PERCENT);
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
					obj.put("abbreviation", rs.getString("abbreviation"));
					obj.put("created_by", rs.getString("created_by"));
					obj.put("updated_by", rs.getString("updated_by"));
					obj.put("created_date", rs.getString("created_date"));
					obj.put("updated_date", rs.getString("updated_date"));
					return obj;
				}
			});
			
			JSONArray array = new JSONArray();
			
			domains.forEach(domain -> {
				array.put(domain);
			});
			return array;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
}

