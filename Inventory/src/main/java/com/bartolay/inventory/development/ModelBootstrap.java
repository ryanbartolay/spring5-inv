package com.bartolay.inventory.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Model;
import com.bartolay.inventory.repositories.ModelRepository;

@Component
public class ModelBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ModelRepository modelRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		Model m = new Model();
		m.setDescription("Test Model");
		m.setBrand(new Brand(1));
		modelRepository.save(m);
		
		Model m2 = new Model();
		m2.setDescription("Test Model 2");
		m2.setBrand(new Brand(2));
		modelRepository.save(m2);
		
	}

}
