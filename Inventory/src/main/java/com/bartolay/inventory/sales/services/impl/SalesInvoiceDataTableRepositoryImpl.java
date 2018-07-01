package com.bartolay.inventory.sales.services.impl;

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
@Qualifier("salesInvoiceDataTableRepository")
public class SalesInvoiceDataTableRepositoryImpl extends RepositoryComponent implements DatatableRepository {
	
	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		String SQL = "SELECT COUNT(b.systemNumber) FROM SalesInvoice b";

		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE b.systemNumber like :search";
		}

		Query query = em.createQuery( SQL);
		if(datatableParameter.getSearch() != null) {
			query.setParameter("search", PERCENT + datatableParameter.getSearch() + PERCENT);
		}

		return (long) query.getSingleResult();
	}

	@Override
	public JSONArray findAllData(DatatableParameter datatableParameter) {

		try{
			DatatableColumn sortColumn = datatableParameter.getSortColumn();
			String SQL = "SELECT t1.system_number, t1.document_number, t1.transaction_date, t1.description, t1.net_total, t1.year, "
					+ "t2.name as location_name, "
					+ "t3.lastname || ', ' || t3.firstname as sales_person, "
					+ "t4.lastname || ', ' || t4.firstname as customer "
					+ "FROM sales_invoice as t1 "
					+ "inner join location as t2 on t1.location_id = t2.id "
					+ "inner join users as t3 on t1.sales_person_id = t3.id "
					+ "inner join users as t4 on t1.customer_id = t4.id";
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
			
			System.err.println(SQL);
			
			List<JSONObject> domains = new ArrayList<>();


			domains = jdbcTemplate.query(SQL, SQL_PARAMS.toArray(), new RowMapper<JSONObject>() {

				@Override
				public JSONObject mapRow(ResultSet rs, int arg1) throws SQLException {
					
					JSONObject obj = new JSONObject();
					obj.put("system_number", rs.getString("system_number"));
					obj.put("document_number", rs.getString("document_number"));
					obj.put("description", rs.getString("description"));
					obj.put("net_total", rs.getBigDecimal("net_total"));
					obj.put("year", rs.getInt("year"));
					obj.put("location_name", rs.getString("location_name"));
					obj.put("transaction_date", rs.getString("transaction_date"));
					obj.put("sales_person", rs.getString("sales_person"));
					obj.put("customer", rs.getString("customer"));
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
