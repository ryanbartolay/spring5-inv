package com.bartolay.inventory.repositories.impl;

import java.math.BigInteger;
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
@Qualifier("modelDatatableRepository")
public class ModelDataTableRepositoryImpl extends RepositoryComponent implements DatatableRepository {
	
	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		String SQL = "SELECT COUNT(m.id) FROM model m inner join brand b on b.id = m.brand_id";

		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE b.name like :name ";
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
			String SQL = "SELECT m.*, b.name as brand_name, b.id as brand_id FROM model m inner join brand b on b.id = m.brand_id ";
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
					obj.put("brand_id", rs.getString("brand_id"));
					obj.put("name", rs.getString("brand_name"));
					obj.put("description", rs.getString("description"));
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

