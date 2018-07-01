package com.bartolay.inventory.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.entity.UserGroup;

@Repository
public class UserJdbcRepository extends RepositoryComponent {
	
	@Autowired
	private UserGroupJdbcRepository userGroupJdbcRepository;
	
	public List<User> retrieveAllUserByUserGroup(UserGroup userGroup, String filter) {
		
		String sql = "select * from users where user_group_id = ?";
		
		Object[] params = new Object[5];
		params[0] =	userGroup.getId();
		
		if(filter != null) {
			sql += " and ("
					+ "lower(username) like ? or "
					+ "lower(firstname) like ? or "
					+ "lower(lastname) like ? or "
					+ "lower(email) like ?"
					+ ")";
			params[1] = "%" + filter.toLowerCase() + "%";
			params[2] = "%" + filter.toLowerCase() + "%";
			params[3] = "%" + filter.toLowerCase() + "%";
			params[4] = "%" + filter.toLowerCase() + "%";
		}
		
		return jdbcTemplate.query(sql, params, new UserRowMapper());
	}
	
	public List<User> retrieveAllSales() {
		return retrieveAllUserByUserGroup(userGroupJdbcRepository.retrieveSales(), null);
	}
	
	public List<User> retrieveAllSales(String filter) {
		return retrieveAllUserByUserGroup(userGroupJdbcRepository.retrieveSales(), filter);
	}
	
	public List<User> retrieveAllCustomers() {
		return retrieveAllUserByUserGroup(userGroupJdbcRepository.retrieveCustomers(), null);
	}
	
	public List<User> retrieveAllCustomers(String filter) {
		return retrieveAllUserByUserGroup(userGroupJdbcRepository.retrieveCustomers(), filter);
	}

	class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));
			user.setAddressCity(rs.getString("address_city"));
			user.setAddressZipcode(rs.getInt("address_zipcode"));
			user.setCreatedDate(rs.getDate("created_date"));
			user.setUpdatedDated(rs.getDate("updated_date"));
			
			return user;
		}
		
	}
	
}
