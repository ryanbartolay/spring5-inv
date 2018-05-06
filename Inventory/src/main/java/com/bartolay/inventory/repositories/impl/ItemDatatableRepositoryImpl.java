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
@Qualifier("itemDatatableRepository")
public class ItemDatatableRepositoryImpl extends RepositoryComponent implements DatatableRepository {

	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		String SQL = "SELECT COUNT(i.id) FROM Item i";

		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE i.name like :search";
		}

		Query query = em.createQuery( SQL);
		if(datatableParameter.getSearch() != null) {
			query.setParameter("search", "%" + datatableParameter.getSearch() + "%");
		}

		return (long) query.getSingleResult();
	}

	@Override
	public JSONArray findAllData(DatatableParameter datatableParameter) {
		try{
			DatatableColumn sortColumn = datatableParameter.getSortColumn();
			String SQL = "SELECT i.*, b.name as brand_name FROM Item i inner join Brand b on b.id = i.brand_id"
					+ " left join Category c on c.id = i.category_id"
					+ " left join Color co on co.id = i.color_id";
			List<Object> SQL_PARAMS = new ArrayList<>();
			
			
			if(datatableParameter.getSearch() != null) {
				SQL += " WHERE i.name like ?";
				SQL_PARAMS.add(datatableParameter.getSearch() + "%");
			}

			// sort order by column
			if(sortColumn != null && datatableParameter.getSortOrder() != null) {
				SQL += " ORDER BY "+sortColumn.getData()+ " " + datatableParameter.getSortOrder().name();	
			}

			if(datatableParameter.getLength() > 0) {
				SQL += " LIMIT " + datatableParameter.getLength() + " OFFSET " + datatableParameter.getStart();
			}
			System.err.println(SQL);
			List<JSONObject> domains = new ArrayList<>();


			domains = jdbcTemplate.query(SQL, SQL_PARAMS.toArray(), new RowMapper<JSONObject>() {

				@Override
				public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
					JSONObject obj = new JSONObject();
					obj.put("id", rs.getLong("id"));
					obj.put("code", rs.getString("code"));
					obj.put("name", rs.getString("name"));
					obj.put("brand_name", rs.getString("brand_name"));
					return obj;
				}
			});
			
			JSONArray array = new JSONArray();
			
			domains.forEach(domain -> {
				array.put(domain);
			});
			return array;
			
		}catch(Exception e){
			return null;
		}

	}

}
