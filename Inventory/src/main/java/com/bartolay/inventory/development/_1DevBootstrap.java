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
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.entity.UserGroup;
import com.bartolay.inventory.form.LocationForm;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.repositories.ClientRepository;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.repositories.CountryRepository;
import com.bartolay.inventory.repositories.SupplierRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserGroupRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.LocationService;
import com.bartolay.inventory.utils.StaticVariables;

@Component
public class _1DevBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	public static final String PASSWORD = "123456a";

	@Autowired
	private UserGroupRepository userGroupRepository;

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
	private ClientRepository clientRepository;

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private UnitRepository unitRepository;

	@Autowired
	private CountryRepository countryRepository;

	private User user;
	
	private Country country;

	@Autowired
	private LocationService locationService;

	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		createCountries();
		country = countryRepository.findById(3).get();
		
		createUserRoot();
		createUserAdministrators();
		createUserSales();
		createUserCustomers();
		createUserSuppliers();
		
		createUserGroups();		
		
		updateUsers();

		user = userRepository.findByUsername("admin");		

		setAuthentication();
		createLocations();
		createCategories();
		createCompaniesAndBrand();
		createSuppliers();
		createColors();

		System.err.println("------------------------------------------");
		System.err.println(categoryRepository.findById(1).get());

		createUnits();	
	}
	
	private User root;
	private User admin;
	
	private User sales1, sales2;
	private User customer1;

	private void createUserRoot() {
		
		root = new User();
		root.setUsername("root");
		root.setPassword(passwordEncoder.encode(PASSWORD));
		root.setFirstName("Root");
		root.setLastName("Root");
		root.setUserGroup(rootUserGroup);
		root.setEmail("root@gmail.com");
		root.setAddress("Manila");
		root.setPhone("09178049704");
		root.setAddressCountry(country);
		root.setDeletable(false);
		userRepository.save(root);
	}
	
	private void createUserAdministrators() {
		admin = new User();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode(PASSWORD));
		admin.setFirstName("Admin");
		admin.setLastName("Admin");
		admin.setUserGroup(adminUserGroup);
		admin.setEmail("admin@gmail.com");
		admin.setAddressCountry(country);
		admin.setCreatedBy(root);
		admin.setDeletable(false);
		userRepository.save(admin);
	}
	
	private void createUserSales() {
		
		sales1 = new User();
		sales1.setUsername("sales1");
		sales1.setPassword(passwordEncoder.encode(_1DevBootstrap.PASSWORD));
		sales1.setFirstName("Sales");
		sales1.setLastName("Sales");
		sales1.setEmail("sales@gmail.com");
		sales1.setAuthority("");
		sales1.setAddressCountry(country);
		sales1.setCreatedBy(admin);
		userRepository.save(sales1);
		
		sales2 = new User();
		sales2.setUsername("sales2");
		sales2.setPassword(passwordEncoder.encode(_1DevBootstrap.PASSWORD));
		sales2.setFirstName("Sales2");
		sales2.setLastName("Sales2");
		sales2.setEmail("sales2@gmail.com");
		sales2.setAuthority("");
		sales2.setAddressCountry(country);
		sales2.setCreatedBy(admin);
		userRepository.save(sales2);
		
	}

	private void createUserCustomers() {
		
		customer1 = new User();
		customer1.setUsername("customer");
		customer1.setPassword(passwordEncoder.encode(PASSWORD));
		customer1.setFirstName("Customer1");
		customer1.setLastName("Customer1");
		customer1.setEmail("customer1@gmail.com");
		customer1.setUserGroup(customerUserGroup);
		customer1.setAddressCountry(country);
		customer1.setCreatedBy(admin);
		userRepository.save(customer1);

	}
	
	private void createUserSuppliers() {
		
	}

	private UserGroup rootUserGroup;
	private UserGroup adminUserGroup;
	private UserGroup salesUserGroup;
	private UserGroup customerUserGroup;
	private UserGroup supplierUserGroup;

	private void createUserGroups() {

		User root = userRepository.findByUsername("root");

		rootUserGroup = new UserGroup();
		rootUserGroup.setName(StaticVariables.ROOT);
		rootUserGroup.setCreatedBy(root);

		userGroupRepository.save(rootUserGroup);

		adminUserGroup = new UserGroup();
		adminUserGroup.setName(StaticVariables.ADMIN);
		adminUserGroup.setCreatedBy(root);

		userGroupRepository.save(adminUserGroup);

		salesUserGroup = new UserGroup();
		salesUserGroup.setName(StaticVariables.SALES);
		salesUserGroup.setCreatedBy(root);

		userGroupRepository.save(salesUserGroup);

		customerUserGroup = new UserGroup();
		customerUserGroup.setName(StaticVariables.CUSTOMERS);
		customerUserGroup.setCreatedBy(root);

		userGroupRepository.save(customerUserGroup);

		supplierUserGroup = new UserGroup();
		supplierUserGroup.setName(StaticVariables.SUPPLIERS);
		supplierUserGroup.setCreatedBy(root);

		userGroupRepository.save(supplierUserGroup);
	}
	
	private void updateUsers() {
		root.setUserGroup(rootUserGroup);
		userRepository.save(root);
		
		admin.setUserGroup(adminUserGroup);
		userRepository.save(admin);
		
		sales1.setUserGroup(salesUserGroup);
		userRepository.save(sales1);
		
		sales2.setUserGroup(salesUserGroup);
		userRepository.save(sales2);
		
		customer1.setUserGroup(customerUserGroup);
		userRepository.save(customer1);
	}

	private void setAuthentication() {

		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, "123456a");
		Authentication auth = authManager.authenticate(authReq);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);

	}

	private void createLocations() {

		LocationForm locationForm = new LocationForm();
		locationForm.setAbbreviation("LPB");
		locationForm.setName("51.1 Tactical Showroom");
		locationForm.setAddress("84 Molave st. Dona Manuela");
		locationForm.setAddressCountry(countryRepository.findById(1).get());
		locationForm.setTelephone("859-1222");
		locationForm.setFax("123456789");
		locationForm.setMobile("09178049704");

		locationService.create(locationForm );

		locationForm = new LocationForm();
		locationForm.setAbbreviation("MST");
		locationForm.setName("Main Store Tactical 1 Showroom");
		locationForm.setAddress("Pook Settlement");
		locationForm.setAddressCountry(countryRepository.findById(2).get());
		locationForm.setTelephone("812322");
		locationForm.setFax("123456789");

		locationService.create(locationForm );


		locationForm = new LocationForm();
		locationForm.setAbbreviation("CS1");
		locationForm.setName("Colonel Shroom");
		locationForm.setAddress("233 El Fuego st. Dasmarinas Village");
		locationForm.setAddressCountry(countryRepository.findById(2).get());
		locationForm.setTelephone("812322");
		locationForm.setFax("123456789");

		locationService.create(locationForm );

		locationForm = new LocationForm();
		locationForm.setAbbreviation("BCT");
		locationForm.setName("Big Colonel Tactical Show room");
		locationForm.setAddress("55 Pearl Bank Center, Valero st.");
		locationForm.setAddressCity("Las Pinas City");
		locationForm.setAddressCountry(countryRepository.findById(3).get());
		locationForm.setTelephone("122244000");
		locationForm.setFax("123456789");
		locationForm.setWebsite("http://www.imbue360.com");
		locationForm.setMobile("09178049704");

		locationService.create(locationForm );

		locationForm = new LocationForm();
		locationForm.setAbbreviation("CT22");
		locationForm.setName("Colonel Trading");
		locationForm.setAddress("Unit 11, 22 Floor Zuellig Bldg");
		locationForm.setAddressCountry(countryRepository.findById(3).get());
		locationForm.setTelephone("122244000");
		locationForm.setFax("123456789");

		locationService.create(locationForm );
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

	

	private void createSuppliers() {
		supplier = new Supplier();
		supplier.setCompany_name("JFC Corporation");
		supplier.setContact_email("bartolay.ryan@gmail.com");
		supplier.setContact_mobile("09178049704");
		supplier.setContact_phone("8093074");
		supplier.setFacebook("rbartolay");
		
		supplier.setBillingAddress("Molave st.");
		supplier.setBillingCity("City of Dreams");
		supplier.setBillingState("NCR");
		supplier.setBillingCountry(countryRepository.findById(1).get());
		supplier.setBillingFax("123456789");
		supplier.setBillingPhone(supplier.getContact_phone());
		supplier.setBillingZipCode("1741");
		
		supplier.setShippingAddress(supplier.getBillingAddress());
		supplier.setShippingCity(supplier.getBillingCity());
		supplier.setShippingState(supplier.getBillingState());
		supplier.setShippingCountry(supplier.getBillingCountry());
		supplier.setShippingFax(supplier.getBillingFax());
		supplier.setShippingPhone(supplier.getBillingPhone());
		supplier.setShippingZipCode(supplier.getBillingZipCode());
		
		supplier.setCurrency("PHP");
		supplier.setNotes("Noted here");
		supplier.setTwitter("rbartolay");
		supplier.setSkype_name("ryan.bartolay");
		supplier.setWebsite("http://www.imbue360.com");
		supplier.setCreatedBy(userRepository.findByUsername("admin"));
		
		supplierRepository.save(supplier);

		supplier = new Supplier();
		supplier.setCompany_name("KFC Food, Inc");
		supplier.setContact_phone("8012222");
		supplier.setBillingAddress("Manila");
		supplier.setCreatedBy(userRepository.findByUsername("admin"));
		supplier.setBillingCountry(countryRepository.findById(2).get());

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
		country1.setAbbreviation("SYR");
		country1.setName("Syria");
		countryRepository.save(country1);

		Country country2 = new Country();
		country2.setAbbreviation("ARE");
		country2.setName("United Arab Emirates");
		countryRepository.save(country2);

		Country country3 = new Country();
		country3.setAbbreviation("PHL");
		country3.setName("Philippines");
		countryRepository.save(country3);
	}
}
