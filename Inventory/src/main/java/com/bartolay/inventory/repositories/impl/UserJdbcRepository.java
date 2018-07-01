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
	
	public List<User> retrieveAllUserByUserGroup(UserGroup userGroup) {
		
		String sql = "select * from users where user_group_id = ?";
		
		return jdbcTemplate.query(sql, new Object[] { userGroup.getId() }, new UserRowMapper());
	}
	
	public List<User> retrieveAllSales() {
		return retrieveAllUserByUserGroup(userGroupJdbcRepository.retrieveSales());
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
