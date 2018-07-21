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
@Qualifier("userGroupDatatableRepository")
public class UserGroupDatatatableRepositoryImpl extends RepositoryComponent implements DatatableRepository {

	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		String SQL = "SELECT COUNT(id) FROM UserGroup";

		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE name like :search"; 
		}

		Query query = em.createQuery( SQL);
		if(datatableParameter.getSearch() != null) {
			query.setParameter("search", datatableParameter.getSearch() + "%");
		}

		return (long) query.getSingleResult();
	}

	@Override
	public JSONArray findAllData(DatatableParameter datatableParameter) {
		try{
			DatatableColumn sortColumn = datatableParameter.getSortColumn();
//			String SQL = "SELECT UG.*, U.firstname, U.lastname FROM user_group as UG INNER JOIN users U ON UG.created_by = U.ID";
			String SQL = "SELECT UG.*, U.firstname, U.lastname, (SELECT array_agg(CONCAT(U.id, '||',U.lastname, '||',U.firstname)) "
					+ "FROM users u where u.user_group_id=UG.id) AS account FROM user_group UG INNER JOIN users U ON UG.created_by = U.ID ";
			List<Object> SQL_PARAMS = new ArrayList<>();
			
			
			if(datatableParameter.getSearch() != null) {
				SQL += " WHERE ug.name like ? or U.firstname LIKE ? or U.lastname LIKE ? or U.username LIKE ? ";
				SQL_PARAMS.add(PERCENT + datatableParameter.getSearch() + PERCENT);
				SQL_PARAMS.add(PERCENT + datatableParameter.getSearch() + PERCENT);
				SQL_PARAMS.add(PERCENT + datatableParameter.getSearch() + PERCENT);
				SQL_PARAMS.add(PERCENT + datatableParameter.getSearch() + PERCENT);
			}
			
			SQL += " GROUP BY UG.id, U.firstname, U.lastname ";
			
			// sort order by column
			if(sortColumn != null && datatableParameter.getSortOrder() != null) {
				SQL += " ORDER BY "+sortColumn.getData()+ " " + datatableParameter.getSortOrder().name();	
			}
			
			if(datatableParameter.getLength() > 0) {
				SQL += " LIMIT " + datatableParameter.getLength() + " OFFSET " + datatableParameter.getStart();
			}
			
			List<JSONObject> domains = new ArrayList<>();
			System.err.println(SQL);
			domains = jdbcTemplate.query(SQL, SQL_PARAMS.toArray(), new RowMapper<JSONObject>() {

				@Override
				public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
					JSONObject obj = new JSONObject();
					obj.put("id", rs.getLong("id"));
					obj.put("name", rs.getString("name"));
					obj.put("create_by", rs.getString("lastname") + ", "+rs.getString("firstname"));
					obj.put("description", rs.getString("description") == null ? "" : rs.getString("description"));
					obj.put("created_date", rs.getString("created_date"));
					obj.put("updated_date", rs.getString("updated_date"));
					obj.put("updated_by", rs.getString("updated_by"));
					obj.put("account", rs.getString("account"));
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
