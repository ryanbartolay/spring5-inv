package com.bartolay.inventory.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.datatable.BrandDatatable;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.pagination.DataTableRequest;
import com.bartolay.inventory.pagination.DataTableResults;
import com.bartolay.inventory.pagination.PaginationCriteria;
import com.bartolay.inventory.repositories.JdbcRepository;
import com.bartolay.inventory.utils.AppUtil;

@Repository
public class BrandJdbcRepository extends JdbcRepository  {
	
	public DataTableResults<BrandDatatable> paginatedFindAll(DataTableRequest<Brand> dataTableInRQ) {
		
		PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
		
		String sql = "select b.id, b.name, c.name as company_name from brand b inner join company c on b.company_id = c.id";
		String sql_count = "select count(*) from brand b inner join company c on b.company_id = c.id";
		
		String paginatedQuery = AppUtil.buildPaginatedQuery(sql, pagination);
		
		System.err.println(paginatedQuery);
		
		DataTableResults<BrandDatatable> dataTableResult = new DataTableResults<BrandDatatable>();
		dataTableResult.setDraw(dataTableInRQ.getDraw());
		
//		dataTableResult.setRecordsTotal(recordsTotal);
		
		
		List<BrandDatatable> brandList = jdbcTemplate.query(paginatedQuery, new Object[] {}, new RowMapper<BrandDatatable>() {

			@Override
			public BrandDatatable mapRow(ResultSet rs, int arg1) throws SQLException {
				BrandDatatable brand = new BrandDatatable();
				brand.setName(rs.getString("name"));
				brand.setCompany_name(rs.getString("company_name"));
				return brand;
			}
		});
		
		dataTableResult.setListOfDataObjects(brandList);
		
		Long count = this.getSQLCount(sql_count);
		dataTableResult.setRecordsTotal(count.toString());
		dataTableResult.setRecordsFiltered(count.toString());
		
		 return dataTableResult;
//		return jdbcTemplate.query(sql, new ResultSetExtractor<JSONObject>() {

	}
	
	public Long getSQLCount(String sql) {
		return jdbcTemplate.queryForObject(sql, new RowMapper<Long>() {

			@Override
			public Long mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getLong(1);
			}
			
		});
	}

}
