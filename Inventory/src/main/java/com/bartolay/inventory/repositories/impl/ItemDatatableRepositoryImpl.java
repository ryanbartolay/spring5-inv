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
		String filter = "";
		if(datatableParameter.getForm() != null) {
			filter = "	INNER JOIN inventory as t5 on t5.item_id=t1.id	WHERE location_id = " + datatableParameter.getForm().get("location").asLong();
		}
		
		try{
			DatatableColumn sortColumn = datatableParameter.getSortColumn();
			String SQL = "SELECT t1.*, b.name as brand_name, t4.name as default_unit_name, " + 
					"array_agg(t3.id) as available_units_id, array_agg(t3.abbreviation) as available_units_abbr, array_agg(t3.name) as available_units_name " + 
					"FROM Item as t1 inner join Brand b on b.id = t1.brand_id " + 
					"left join item_units as t2 on t1.id = t2.item_id " + 
					"inner join unit as t3 on t2.unit_id = t3.id " +
					"inner join unit as t4 on t1.default_unit_id = t4.id " +
					filter;
			
			List<Object> SQL_PARAMS = new ArrayList<>();
			
			
			if(datatableParameter.getSearch() != null) {
				if(filter.trim().isEmpty()) {
					SQL += " WHERE t1.name like ?";
				}else {
					SQL += " and t1.name like ?";
				}
				
				SQL_PARAMS.add(datatableParameter.getSearch() + "%");
			}
			
			SQL += " group by t1.id, b.name, t2.item_id, t4.name";

			// sort order by column
			if(sortColumn != null && datatableParameter.getSortOrder() != null) {
				SQL += " ORDER BY "+sortColumn.getData()+ " " + datatableParameter.getSortOrder().name();	
			}

			if(datatableParameter.getLength() > 0) {
				SQL += " LIMIT " + datatableParameter.getLength() + " OFFSET " + datatableParameter.getStart();
			}
			
			System.err.println(SQL);
			List<JSONObject> domains = new ArrayList<>();

//			select array_agg(t3.id) as available_units_id, array_agg(t3.abbreviation) as available_units_abbr, array_agg(t3.name) as available_units_name from item as t1 
//			left join item_units as t2 on t1.id = t2.item_id
//			inner join unit as t3 on t2.unit_id = t3.id
//			where t1.id = 1
//			group by t2.item_id

			domains = jdbcTemplate.query(SQL, SQL_PARAMS.toArray(), new RowMapper<JSONObject>() {

				@Override
				public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
					JSONObject obj = new JSONObject();
					obj.put("id", rs.getLong("id"));
					obj.put("code", rs.getString("code"));
					obj.put("name", rs.getString("name"));
					obj.put("brand_name", rs.getString("brand_name"));
					obj.put("default_unit_id", rs.getString("default_unit_id"));
					obj.put("default_unit_name", rs.getString("default_unit_name"));
					obj.put("available_units_id", rs.getString("available_units_id"));
					obj.put("available_units_abbr", rs.getString("available_units_abbr"));
					obj.put("available_units_name", rs.getString("available_units_name"));
					obj.put("minimum_item_price", rs.getBigDecimal("minimum_item_price"));
					obj.put("maximum_item_price", rs.getBigDecimal("maximum_item_price"));
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
