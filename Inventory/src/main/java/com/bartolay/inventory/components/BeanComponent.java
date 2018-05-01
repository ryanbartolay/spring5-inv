package com.bartolay.inventory.components;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public abstract class BeanComponent extends HttpBeanComponent{

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

}