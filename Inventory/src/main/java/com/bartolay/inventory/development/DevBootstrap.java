package com.bartolay.inventory.development;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bartolay.enums.AccountType;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.entity.Product;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.utils.StringUtils;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private static Session session;
	private String password = "123456a";
	
	private Supplier supplier;
	private Category category;
	
	@Autowired
	private UserRepository employeeRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
//		session.beginTransaction();
		createEmployees();
//		createSuppliers();
//		createCategories();
//		createProducts();
//      session.getTransaction().commit();
		createBrand();
	}
	
	private void createBrand() {
		Brand brand = new Brand();
		brand.setCreatedBy(employeeRepository.findByUsername("admin"));
		brandRepository.save(brand);
	}

	private void createEmployees() {
		User employee = new User();
		employee.setUsername("admin");
		employee.setPassword(passwordEncoder.encode(password));
		employee.setFirstName("Admin");
		employee.setLastName("Admin");
		employee.setType("admin");
		employee.setAuthority(AccountType.SUPERADMIN.toString());
		employeeRepository.save(employee);
	}
	
	private void createSuppliers() {
		supplier = new Supplier();
		supplier.setName("JFC Corporation");
		supplier.setPhone("91-100");
		supplier.setAddress("Las Pinas");
		session.save(supplier);
	}
	
	private void createCategories() {
		category = new Category();
		category.setType("Computers - Hardware");
		category.setDescription("IT supplies for hardware");
		session.save(category);
	}
	
	private void createProducts() {
		Product product = new Product();
		product.setProductName("Kingston DDR3 Mega RAM");
		product.setQuantity(0);
		product.setPrice(1.156);
		product.setSupplier(supplier);
		product.setCategory(category);
		session.save(product);
	}
}
