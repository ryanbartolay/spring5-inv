package com.bartolay.inventory.development;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.entity.Color;
import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.entity.Country;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.AccountType;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.repositories.CountryRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.SupplierRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	public static final String PASSWORD = "123456a";
	
	@Autowired
	private AuthenticationManager authManager;

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
	private LocationRepository locationRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	private User user;

	@Override
	public int getOrder() {
		return 1;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		createEmployees();
		
		user = userRepository.findByUsername("admin");		
		
		setAuthentication();
		createLocations();
		createCategories();
		createCompaniesAndBrand();
		createSuppliers();
		createColors();
		
		System.err.println("------------------------------------------");
		System.err.println(categoryRepository.findById((long) 1).get());

		
		createUnits();
		
		createCountries();
	}


	private void setAuthentication() {

		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, "123456a");
		Authentication auth = authManager.authenticate(authReq);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
				
	}

	private void createLocations() {
		Location location = new Location();
		location.setName("51.1 Tactical Showroom");
		location.setFax("21315 local 442");
		location.setEnabled(true);
		
		locationRepository.save(location);
		
		Location location2 = new Location();
		location2.setName("Main Store Tactical 1 Showroom");
		location2.setAbbreviation("MSR2");
		location2.setEnabled(true);
		
		locationRepository.save(location2);
		
		Location location3 = new Location();
		location3.setName("Colonel Shroom");
		location3.setAddress("Manila, Philippines");
		location3.setTelephone("029011234");
		location3.setEnabled(true);
		
		locationRepository.save(location3);
		
		Location location4 = new Location();
		location4.setName("Big Colonel Tactical Show room");
		location4.setTelephone("10022-4233220");
		location4.setEnabled(true);
		
		locationRepository.save(location4);
		
		Location location5 = new Location();
		location5.setName("Colonel Trading");
		location5.setAddress("Jeddah, Saudi Arabia");
		location5.setEnabled(true);
		
		locationRepository.save(location5);
	}


	private void createUnits() {
		
		List<Unit> units = new ArrayList<>();
		
		Unit unit1 = new Unit();
		unit1.setCreatedBy(user);
		unit1.setAbbreviation("pcs");
		unit1.setName("Pieces");
		units.add(unit1);
		
		Unit unit2 = new Unit();
		unit2.setAbbreviation("pair");
		unit2.setName("Pair");
		unit2.setCreatedBy(user);
		units.add(unit2);
		
		Unit unit3 = new Unit();
		unit3.setAbbreviation("cb");
		unit3.setName("Cubic");
		unit3.setCreatedBy(user);
		units.add(unit3);
		
		Unit unit4 = new Unit();
		unit4.setAbbreviation("meter");
		unit4.setName("Meter");
		unit4.setCreatedBy(user);
		units.add(unit4);
		
		Unit unit5 = new Unit();
		unit5.setAbbreviation("ea");
		unit5.setName("Each");
		unit5.setCreatedBy(user);
		units.add(unit5);
		
		Unit unit6 = new Unit();
		unit6.setAbbreviation("box");
		unit6.setName("Box");
		unit6.setCreatedBy(user);
		units.add(unit6);
		
		unitRepository.saveAll(units);
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
		root.setPassword(passwordEncoder.encode(PASSWORD));
		root.setFirstName("Root");
		root.setLastName("Root");
		root.setAccountType(AccountType.ROOT);
		root.setEmail("bartolay.ryan@gmail.com");
		root.setAuthority(AccountType.ROOT.toString());
		userRepository.save(root);
		
		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode(PASSWORD));
		user.setFirstName("Admin");
		user.setLastName("Admin");
		user.setAccountType(AccountType.ADMIN);
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

	private void createCountries() {
		Country country1 = new Country();
		country1.setCode("SYR");
		country1.setName("Syria");
		countryRepository.save(country1);
		
		Country country2 = new Country();
		country2.setCode("ARE");
		country2.setName("United Arab Emirates");
		countryRepository.save(country2);
	}
}
