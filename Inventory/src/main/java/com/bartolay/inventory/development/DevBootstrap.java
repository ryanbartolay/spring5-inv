package com.bartolay.inventory.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bartolay.enums.AccountType;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.entity.Color;
import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Product;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.SupplierRepository;
import com.bartolay.inventory.repositories.UserRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private String password = "123456a";

	private Supplier supplier;
	private Category category;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		createEmployees();
		createCategories();
		createCompaniesAndBrand();
		createSuppliers();
		createColors();
		
		System.err.println("------------------------------------------");
		System.err.println(categoryRepository.findById((long) 1).get());
		// need to create this as last
		createItems();
	}

	private void createColors() {
		Color red = new Color();
		red.setName("red");	
		colorRepository.save(red);
		
		Color blue = new Color();
		blue.setName("blue");
		
		colorRepository.save(blue);
		
		Color green = new Color();
		green.setName("green");
		
		colorRepository.save(green);
		
		Color black = new Color();
		black.setName("black");
		
		colorRepository.save(black);
	}

	private void createItems() {
		Item item = new Item();
		item.setBrand(brandRepository.findById((long) 1).get());
		item.setColor(colorRepository.findByName("red"));
		item.setCategory(categoryRepository.findById((long) 1).get());
		item.setCode("ryan1234");
		item.setName("HyperDunk Series X 2");
		item.setCreatedBy(userRepository.findByUsername("admin"));
		item.setEnabled(true);
		
		itemRepository.save(item);
		Item item2 = new Item();
		item2.setBrand(brandRepository.findById((long) 1).get());
		item2.setColor(colorRepository.findByName("black"));
		item2.setCategory(categoryRepository.findById((long) 2).get());
		item2.setCode("ryan1235");
		item2.setName("HyperDunk Series X 3");
		item2.setCreatedBy(userRepository.findByUsername("admin"));
		item2.setEnabled(true);
		
		itemRepository.save(item2);
	}

	private void createCompaniesAndBrand() {
		// creating companies
		Company c = new Company();
		c.setName("Big Trading");
		c.setEmail("rbartolay.gotechsolutions@gmail.com");
		c.setCreatedBy(userRepository.findByUsername("admin"));
		companyRepository.save(c);

		Company company = new Company();
		company.setName("GoTech Solutions");
		company.setEmail("bartolay.ryan@gmail.com");
		company.setCreatedBy(userRepository.findByUsername("admin"));
		companyRepository.save(company);

		// creating brands
		Brand brand = new Brand();
		brand.setName("Nike");
		brand.setNameArabic("تفصيل بدون قماش");
		brand.setCreatedBy(userRepository.findByUsername("admin"));
		brand.setCompany(company);
		brand.setEnabled(true);

		brandRepository.save(brand);

		// creating brands
		Brand brand2 = new Brand();
		brand2.setName("Adidas");
		brand2.setNameArabic("تفصيل بدون قماش");
		brand2.setCreatedBy(userRepository.findByUsername("admin"));
		brand2.setCompany(c);
		brand2.setEnabled(true);

		brandRepository.save(brand2);

		// creating brands
		Brand brand3 = new Brand();
		brand3.setName("And1");
		brand3.setNameArabic("تفصيل بدون قماش");
		brand3.setCreatedBy(userRepository.findByUsername("admin"));
		brand3.setCompany(c);
		brand3.setEnabled(true);

		brandRepository.save(brand3);
	}

	private void createEmployees() {
		User root = new User();
		root.setUsername("root");
		root.setPassword(passwordEncoder.encode(password));
		root.setFirstName("Root");
		root.setLastName("Root");
		root.setType("ROOT");
		root.setEmail("bartolay.ryan@gmail.com");
		root.setAuthority(AccountType.ROOT.toString());
		userRepository.save(root);
		
		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode(password));
		user.setFirstName("Admin");
		user.setLastName("Admin");
		user.setType("ADMIN");
		user.setEmail("bartolay.ryan@gmail.com");
		user.setAuthority(AccountType.ADMIN.toString());
		userRepository.save(user);
	}

	private void createSuppliers() {
		supplier = new Supplier();
		supplier.setName("JFC Corporation");
		supplier.setPhone("91-100");
		supplier.setAddress("Las Pinas");
		
		supplierRepository.save(supplier);
	}

	private void createCategories() {
		category = new Category();
		category.setName("Computers - Hardware");
		category.setDescription("IT supplies for hardware");
		category.setEnabled(true);
		categoryRepository.save(category);
		
		category = new Category();
		category.setName("Computers - Software");
		category.setDescription("Drivers and Installers");
		category.setEnabled(true);
		categoryRepository.save(category);
	}

	private void createProducts() {
		Product product = new Product();
		product.setProductName("Kingston DDR3 Mega RAM");
		product.setQuantity(0);
		product.setPrice(1.156);
		product.setSupplier(supplier);
		product.setCategory(category);
	}
}
