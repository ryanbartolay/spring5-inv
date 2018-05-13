package com.bartolay.inventory.development;

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
			String SQL = "SELECT * FROM sales_invoice ";
			List<Object> SQL_PARAMS = new ArrayList<>();
			
			
			if(datatableParameter.getSearch() != null) {
				SQL += " WHERE b.system_number like ?";
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
					System.err.println(rs.getString("system_number"));
					JSONObject obj = new JSONObject();
					obj.put("system_number", rs.getString("system_number"));
					obj.put("description", rs.getString("description") == null ? EMPTY : rs.getString("description"));
					obj.put("created_date", rs.getString("created_date"));
					obj.put("code", rs.getString("created_date"));
					obj.put("unit", rs.getString("created_date"));
					obj.put("quantity", rs.getString("created_date"));
					obj.put("unit_price", rs.getString("created_date"));
					obj.put("amount", rs.getString("created_date"));
					obj.put("document_number", rs.getString("document_number"));
					obj.put("payment_method", rs.getString("payment_method"));
					obj.put("sales_person_id", rs.getString("sales_person_id"));
					obj.put("transaction_date", rs.getString("transaction_date"));
					obj.put("year", rs.getString("year"));
					obj.put("created_by_id", rs.getString("created_by_id"));
					obj.put("credit_card_details_id", rs.getString("credit_card_details_id"));
					obj.put("location_id", rs.getString("location_id"));
					obj.put("updated_by_id", rs.getString("updated_by_id"));
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
