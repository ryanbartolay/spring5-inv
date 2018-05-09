package com.bartolay.inventory.stock.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.stock.entity.StockOpening;

@Repository
public class StockOpeningJdbcRepository extends RepositoryComponent {
	
	/**
	 * Returns the latest system number of stock opening
	 * @param location_id
	 * @param year
	 * @return
	 */
	public String getMaxSystemNumber(int location_id, int year) {
		String sql = "select max(system_number) from " + StockOpening.TABLE_NAME 
				+ " where location_id = ? and year(transaction_date) = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] { location_id, year }, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("system_number");
			}
			
		});
	}

}
