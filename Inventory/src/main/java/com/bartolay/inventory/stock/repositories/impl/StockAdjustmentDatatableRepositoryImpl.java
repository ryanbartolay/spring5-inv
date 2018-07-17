package com.bartolay.inventory.stock.repositories.impl;

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
@Qualifier("stockAdjustmentDatatableRepository")
public class StockAdjustmentDatatableRepositoryImpl extends RepositoryComponent implements DatatableRepository {

	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		String SQL = "SELECT COUNT(system_number) FROM stock_adjustment ";

		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE  documentNumber like :documentNumber ";
		}

		Query query = em.createNativeQuery(SQL);
		if(datatableParameter.getSearch() != null) {
			query.setParameter("documentNumber", PERCENT + datatableParameter.getSearch() + PERCENT);
		}
		return Long.parseLong(query.getSingleResult().toString());
	}

	@Override
	public JSONArray findAllData(DatatableParameter datatableParameter) {
		try{
			DatatableColumn sortColumn = datatableParameter.getSortColumn();
			String SQL = "SELECT t1.system_number, t1.document_number, t1.transaction_date, t1.stock_adjustment_type, t1.description, "
					+ "t2.name as location_name, "
					+ "t3.lastname || ', ' || t3.firstname as created_by, "
					+ "t4.description as reason "
					+ "FROM stock_adjustment t1 "
					+ "inner join location t2 on t1.location_id = t2.id "
					+ "inner join users as t3 on t1.created_by = t3.id "
					+ "inner join stock_adjustment_reason as t4 on t1.stock_adjustment_reason_id = t4.id";
			List<Object> SQL_PARAMS = new ArrayList<>();
			
			
			if(datatableParameter.getSearch() != null) {
				SQL += " WHERE t1.system_number like ?";
				SQL_PARAMS.add(datatableParameter.getSearch() + PERCENT);
			}

			// sort order by column
			if(sortColumn != null && datatableParameter.getSortOrder() != null) {
				SQL += " ORDER BY t1."+sortColumn.getData()+ " " + datatableParameter.getSortOrder().name();	
			}
			
			if(datatableParameter.getLength() > 0) {
				SQL += " LIMIT " + datatableParameter.getLength() + " OFFSET " + datatableParameter.getStart();
			}
			
			List<JSONObject> domains = new ArrayList<>();
			
			domains = jdbcTemplate.query(SQL, SQL_PARAMS.toArray(), new RowMapper<JSONObject>() {

				@Override
				public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
					JSONObject obj = new JSONObject();
					obj.put("system_number", rs.getString("system_number"));
					obj.put("document_number", rs.getString("document_number"));
					obj.put("description", rs.getString("description"));
					obj.put("transaction_date", rs.getString("transaction_date"));
					obj.put("stock_adjustment_type", rs.getString("stock_adjustment_type"));
					obj.put("location_name", rs.getString("location_name"));
					obj.put("created_by", rs.getString("created_by"));
					obj.put("reason", rs.getString("reason"));
					
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
