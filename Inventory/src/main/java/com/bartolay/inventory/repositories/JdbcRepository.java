package com.bartolay.inventory.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class JdbcRepository {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
}
