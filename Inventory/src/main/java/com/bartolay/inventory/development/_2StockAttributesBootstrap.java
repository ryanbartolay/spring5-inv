package com.bartolay.inventory.development;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.ItemUnit;
import com.bartolay.inventory.entity.Model;
import com.bartolay.inventory.entity.Size;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.repositories.ExpenseRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.ItemUnitRepository;
import com.bartolay.inventory.repositories.ModelRepository;
import com.bartolay.inventory.repositories.SizeRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserRepository;

@Component
public class _2StockAttributesBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemUnitRepository itemUnitRepository;
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
	@Autowired
	private ExpenseRepository expenseRepository;

	private User admin;

	@Override
	public int getOrder() {
		return 2;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		admin = userRepository.findByUsername("admin");

		Model m = new Model();
		m.setName("Test Model");
		m.setBrand(new Brand(1));
		modelRepository.save(m);

		Model m2 = new Model();
		m2.setName("Test Model 2");
		m2.setBrand(new Brand(2));
		modelRepository.save(m2);

		createSizes();

		createItems();
		
		createExpenses();
	}
	
	private void createExpenses() {

		Expense e1 = new Expense();
		e1.setDescription("Transportation");
		e1.setCreatedBy(admin);
		expenseRepository.save(e1);
		
		Expense e2 = new Expense();
		e2.setDescription("Other");
		e2.setCreatedBy(admin);
		expenseRepository.save(e2);
		
		Expense e3 = new Expense();
		e3.setDescription("Food");
		e3.setCreatedBy(admin);
		expenseRepository.save(e3);
		
		Expense e4 = new Expense();
		e4.setDescription("Medical");
		e4.setCreatedBy(admin);
		expenseRepository.save(e4);
		
		Expense e5 = new Expense();
		e5.setDescription("Rebate Cancelled");
		e5.setCreatedBy(admin);
		expenseRepository.save(e5);
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
		Set<ItemUnit> itemUnits = new HashSet<>();
		
		ItemUnit itemUnit1 = new ItemUnit();
		itemUnit1.setUnit(unitRepository.findById(1).get());
		itemUnit1.setRate(new BigDecimal("221.441"));
		
		ItemUnit itemUnit2 = new ItemUnit();
		itemUnit2.setUnit(unitRepository.findById(2).get());
		itemUnit2.setRate(new BigDecimal("41.1111"));
		
		ItemUnit itemUnit3 = new ItemUnit();
		itemUnit3.setUnit(unitRepository.findById(3).get());
		itemUnit3.setRate(new BigDecimal("0.1111"));
		
		itemUnits.add(itemUnit1);
		itemUnits.add(itemUnit2);
		itemUnits.add(itemUnit3);
		
		
		Item item = new Item();
		item.setBrand(brandRepository.findById(1).get());
		item.setColor(colorRepository.findByName("red"));
		item.setCategory(categoryRepository.findById(1).get());
		item.setCode("ryan1234");
		item.setDefaultUnit(unitRepository.findById(1).get());
		item.setName("HyperDunk Series X 2");
		item.setCreatedBy(admin);
		item.setEnabled(true);
		item.setSize(new Size(1));
		item.setModel(new Model(1));
		item.setItemUnits(itemUnits);
		item.setMinimumUnitPrice(new BigDecimal("50.75"));
		itemRepository.save(item);
		
		for(ItemUnit itemUnit : itemUnits) {
			itemUnit.setItem(item);
		}
		
		itemUnitRepository.saveAll(itemUnits);
		
		itemUnits.clear();
		
		itemUnit1 = new ItemUnit();
		itemUnit1.setUnit(unitRepository.findById(1).get());
		itemUnit1.setRate(new BigDecimal("9821.441"));
		
		itemUnit2 = new ItemUnit();
		itemUnit2.setUnit(unitRepository.findById(3).get());
		itemUnit2.setRate(new BigDecimal("101.9911"));
		
		itemUnit3 = new ItemUnit();
		itemUnit3.setUnit(unitRepository.findById(5).get());
		itemUnit3.setRate(new BigDecimal("0.1111"));
		
		ItemUnit itemUnit4 = new ItemUnit();
		itemUnit4.setUnit(unitRepository.findById(6).get());
		itemUnit4.setRate(new BigDecimal("1213.191"));
		
		itemUnits.add(itemUnit1);
		itemUnits.add(itemUnit2);
		itemUnits.add(itemUnit3);
		itemUnits.add(itemUnit4);
		
		Item item2 = new Item();
		item2.setBrand(brandRepository.findById(1).get());
		item2.setColor(colorRepository.findByName("black"));
		item2.setCategory(categoryRepository.findById(2).get());
		item2.setCode("ryan1235");
		item2.setDefaultUnit(unitRepository.findById(2).get());
		item2.setName("HyperDunk Series X 3");
		item2.setCreatedBy(admin);
		item2.setEnabled(true);
		item2.setSize(new Size(1));
		item2.setModel(new Model(2));
		item2.setItemUnits(itemUnits);
		item2.setMinimumUnitPrice(new BigDecimal("100.75"));
		item2.setMaximumUnitPrice(new BigDecimal("110.759"));
		itemRepository.save(item2);
		
		for(ItemUnit itemUnit : itemUnits) {
			itemUnit.setItem(item2);
		}
		
		itemUnitRepository.saveAll(itemUnits);
		
		
		itemUnits.clear();
		
		itemUnit1 = new ItemUnit();
		itemUnit1.setUnit(unitRepository.findById(4).get());
		itemUnit1.setRate(new BigDecimal("9821.441"));
		
		itemUnit2 = new ItemUnit();
		itemUnit2.setUnit(unitRepository.findById(3).get());
		itemUnit2.setRate(new BigDecimal("101.9911"));
		
		itemUnit3 = new ItemUnit();
		itemUnit3.setUnit(unitRepository.findById(5).get());
		itemUnit3.setRate(new BigDecimal("0.1111"));
		
		itemUnit4 = new ItemUnit();
		itemUnit4.setUnit(unitRepository.findById(6).get());
		itemUnit4.setRate(new BigDecimal("1213.191"));
		
		itemUnits.add(itemUnit1);
		itemUnits.add(itemUnit2);
		itemUnits.add(itemUnit3);
		itemUnits.add(itemUnit4);
		
		Item item3 = new Item();
		item3.setBrand(brandRepository.findById(2).get());
		item3.setColor(colorRepository.findByName("green"));
		item3.setCategory(categoryRepository.findById(1).get());
		item3.setCode("1123EW");
		item3.setDefaultUnit(unitRepository.findById(3).get());
		item3.setName("Jordan 1 Sneakers");
		item3.setCreatedBy(admin);
		item3.setEnabled(true);
		item3.setSize(new Size(4));
		item3.setModel(new Model(2));
		item3.setItemUnits(itemUnits);
		item3.setMinimumUnitPrice(new BigDecimal("10"));
		item3.setMaximumUnitPrice(new BigDecimal("200"));
		itemRepository.save(item3);
		
		for(ItemUnit itemUnit : itemUnits) {
			itemUnit.setItem(item3);
		}
		
		itemUnitRepository.saveAll(itemUnits);
	}
}
