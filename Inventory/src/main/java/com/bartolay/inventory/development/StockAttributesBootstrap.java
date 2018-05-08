package com.bartolay.inventory.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Model;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.ModelRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserRepository;

@Component
public class StockAttributesBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	
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
		
		
		createItems();
		
	}

	private void createItems() {
		Item item = new Item();
		item.setBrand(brandRepository.findById((long) 1).get());
		item.setColor(colorRepository.findByName("red"));
		item.setCategory(categoryRepository.findById((long) 1).get());
		item.setCode("ryan1234");
		item.setDefaultUnit(unitRepository.findById(1).get());
		item.setName("HyperDunk Series X 2");
		item.setCreatedBy(userRepository.findByUsername("admin"));
		item.setEnabled(true);
		item.setSize("Small");
		item.setModel(new Model(1L));
		itemRepository.save(item);
		
		Item item2 = new Item();
		item2.setBrand(brandRepository.findById((long) 1).get());
		item2.setColor(colorRepository.findByName("black"));
		item2.setCategory(categoryRepository.findById((long) 2).get());
		item2.setCode("ryan1235");
		item2.setDefaultUnit(unitRepository.findById(2).get());
		item2.setName("HyperDunk Series X 3");
		item2.setCreatedBy(userRepository.findByUsername("admin"));
		item2.setEnabled(true);
		item2.setSize("Big");
		item2.setModel(new Model(2L));
		itemRepository.save(item2);
	}
}
