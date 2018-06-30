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
@Qualifier("salesReturnDataTableRepository")
public class SalesReturnDataTableRepositoryImpl extends RepositoryComponent implements DatatableRepository {
	
	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		String SQL = "SELECT COUNT(b.id) FROM Brand b";

		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE b.name like :search";
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
			String SQL = "SELECT t1.*, " + 
					"	concat_ws(' ', u1.firstName, u1.lastName) as created_by, " + 
					"	concat_ws(' ', u3.firstName, u3.lastName) as customer, " + 
					"	t3.name, " + 
					"	(SELECT COUNT(ID) FROM sales_invoice_item WHERE sales_invoice_system_number=t2.system_number )as item_count," + 
					"	concat_ws(' ', u4.firstName, u4.lastName) as sales_person , " + 
					"	t2.net_total as return_amount, " + 
					"	t2.created_date as sales_invoice_created_date " + 
					"	FROM sales_return t1 " + 
					"	INNER JOIN users u1 on u1.id=t1.created_by_id " + 
					"	INNER JOIN users u2 on u2.id=t1.updated_by_id " + 
					"	INNER JOIN sales_invoice t2 on t2.system_number=t1.sales_invoice_system_number " + 
					"	INNER JOIN location t3 on t3.id = t2.location_id " + 
					"	INNER JOIN users u3 on u3.id = t2.customer_id " +
					"   INNER JOIN users u4 on u4.id = t2.sales_person_id";
					
			List<Object> SQL_PARAMS = new ArrayList<>();
			
			
			if(datatableParameter.getSearch() != null) {
				SQL += " WHERE b.name like ?";
				SQL_PARAMS.add(datatableParameter.getSearch() + "%");
			}
			
			
			System.err.println(sortColumn);
			
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
					obj.put("sales_invoice_system_number", rs.getString("sales_invoice_system_number"));
					obj.put("sales_invoice_created_date", rs.getDate("sales_invoice_created_date"));
					obj.put("location", rs.getString("name"));
					obj.put("sales_person", rs.getString("sales_person"));
					obj.put("customer", rs.getString("customer"));
					obj.put("item_count", rs.getString("item_count"));
					obj.put("net_total", rs.getString("return_amount"));
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
			return new JSONArray();
		}	
	}
}
