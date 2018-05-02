package com.bartolay.inventory.components;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public abstract class RepositoryComponent {

	@Autowired
	protected ObjectMapper objectMapper;
	@PersistenceContext
	protected EntityManager em;
	//	@Autowired
	//	protected Message message;
	//	@Autowired
	//	protected DatatableForm datatableForm;
	//	@Autowired
	//	protected DateTimeFormatUtil dateTimeFormatUtil;
	@Autowired
	protected RestTemplate restTemplate;
	//	@Autowired
	//	protected AuthorizationCodeResourceDetails client;

	public Timestamp getTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	protected Long getQueryCount(String sql) {
		Query query = em.createQuery(sql, Long.class);
		return (Long) query.getSingleResult();
	}
}