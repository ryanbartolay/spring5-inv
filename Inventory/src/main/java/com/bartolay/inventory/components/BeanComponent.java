package com.bartolay.inventory.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public abstract class BeanComponent {

	@Autowired
	protected ObjectMapper objectMapper;
	
}
