package com.bartolay.inventory.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.datatable.BrandDatatable;
import com.bartolay.inventory.repositories.JdbcRepository;

@Repository
public class BrandJdbcRepository extends JdbcRepository  {
	
	public List<BrandDatatable> paginatedFindAll(String sql) {
		
		return jdbcTemplate.query(sql, new Object[] {}, new RowMapper<BrandDatatable>() {

			@Override
			public BrandDatatable mapRow(ResultSet rs, int arg1) throws SQLException {
				BrandDatatable brand = new BrandDatatable();
				brand.setName(rs.getString("name"));
				brand.setCompany_name(rs.getString("company_name"));
				return brand;
			}
		});
//		return jdbcTemplate.query(sql, new ResultSetExtractor<JSONObject>() {

	}

}
