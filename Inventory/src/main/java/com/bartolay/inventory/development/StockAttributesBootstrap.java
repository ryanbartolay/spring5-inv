package com.bartolay.inventory.development;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Model;
import com.bartolay.inventory.entity.Size;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.ModelRepository;
import com.bartolay.inventory.repositories.SizeRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserRepository;

@Component
public class StockAttributesBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

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
	@Autowired
	private SizeRepository sizeRepository;

	private User admin;

	@Override
	public int getOrder() {
		return 2;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		admin = userRepository.findByUsername("admin");

		Model m = new Model();
		m.setDescription("Test Model");
		m.setBrand(new Brand(1));
		modelRepository.save(m);

		Model m2 = new Model();
		m2.setDescription("Test Model 2");
		m2.setBrand(new Brand(2));
		modelRepository.save(m2);

		createSizes();

		createItems();

	}

	private void createSizes() {
		List<Size> sizes = new ArrayList<>();

		Size size1 = new Size();
		size1.setCreatedBy(admin);
		size1.setName("XXXS");

		sizes.add(size1);

		Size size2 = new Size();
		size2.setCreatedBy(admin);
		size2.setName("XXS");

		sizes.add(size2);

		Size size3 = new Size();
		size3.setCreatedBy(admin);
		size3.setName("XS");

		sizes.add(size3);

		Size size4 = new Size();
		size4.setCreatedBy(admin);
		size4.setName("S");

		sizes.add(size4);

		Size size5 = new Size();
		size5.setCreatedBy(admin);
		size5.setName("M");

		sizes.add(size5);

		Size size6 = new Size();
		size6.setCreatedBy(admin);
		size6.setName("L");

		sizes.add(size6);

		Size size7 = new Size();
		size7.setCreatedBy(admin);
		size7.setName("XL");

		sizes.add(size7);

		Size size8 = new Size();
		size8.setCreatedBy(admin);
		size8.setName("XXL");

		sizes.add(size8);

		Size size9 = new Size();
		size9.setCreatedBy(admin);
		size9.setName("XXXL");

		sizes.add(size9);

		Size size10 = new Size();
		size10.setCreatedBy(admin);
		size10.setName("Jumbo");

		sizes.add(size10);
		
		sizeRepository.saveAll(sizes);
	}

	private void createItems() {
		Item item = new Item();
		item.setBrand(brandRepository.findById((long) 1).get());
		item.setColor(colorRepository.findByName("red"));
		item.setCategory(categoryRepository.findById((long) 1).get());
		item.setCode("ryan1234");
		item.setDefaultUnit(unitRepository.findById(1).get());
		item.setName("HyperDunk Series X 2");
		item.setCreatedBy(admin);
		item.setEnabled(true);
		item.setSize(new Size(1));
		item.setModel(new Model(1L));
		itemRepository.save(item);

		Item item2 = new Item();
		item2.setBrand(brandRepository.findById((long) 1).get());
		item2.setColor(colorRepository.findByName("black"));
		item2.setCategory(categoryRepository.findById((long) 2).get());
		item2.setCode("ryan1235");
		item2.setDefaultUnit(unitRepository.findById(2).get());
		item2.setName("HyperDunk Series X 3");
		item2.setCreatedBy(admin);
		item2.setEnabled(true);
		item2.setSize(new Size(1));
		item2.setModel(new Model(2L));
		itemRepository.save(item2);
	}
}
