package com.bartolay.inventory.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.entity.UserGroup;
import com.bartolay.inventory.utils.StaticVariables;

@Repository
public class UserGroupJdbcRepository extends RepositoryComponent {

	public List<UserGroup> retrieveAll() {
		String sql = "select * from user_group";
		return jdbcTemplate.query(sql, new UserGroupRowMapper());
	}
	
	public UserGroup retrieveSales() {
		String sql = "select * from user_group where lower(name) = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {StaticVariables.SALES.toLowerCase()}, new UserGroupRowMapper());
	}
	
	public UserGroup retrieveCustomers() {
		String sql = "select * from user_group where lower(name) = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {StaticVariables.CUSTOMERS.toLowerCase()}, new UserGroupRowMapper());
	}
	
	class UserGroupRowMapper implements RowMapper<UserGroup> {

		@Override
		public UserGroup mapRow(ResultSet rs, int arg1) throws SQLException {
			UserGroup userGroup = new UserGroup();
			userGroup.setId(rs.getInt("id"));
			userGroup.setCreatedDate(rs.getDate("created_date"));
			userGroup.setDescription(rs.getString("description"));
			userGroup.setName(rs.getString("name"));
			userGroup.setUpdatedDated(rs.getDate("updated_date"));
			return userGroup;
		}
		
	}

}
