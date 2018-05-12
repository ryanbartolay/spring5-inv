package com.bartolay.inventory.development;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.AccountType;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.sales.services.SalesInvoiceService;

@Component
public class SaleBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered  {

	@Autowired
	private CreditCardDetailsRepository creditCardDetailsRepository;
	
	@Autowired
	private SalesInvoiceService salesInvoiceService;

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private User user;
	
	private User sales1;
	private User sales2;
	
	private CreditCardDetails ccDetails;
	private CreditCardDetails ccDetails2;
	
	@Override
	public int getOrder() {
		return 4;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		user = userRepository.findById(2).get();
		
		ccDetails = new CreditCardDetails();
		ccDetails.setCardNumber("12315-51882-0932");
		ccDetails.setHoldersName("RYAN KRISTOFFER BARTOLAY");
		ccDetails.setMonthExpiry(2);
		ccDetails.setYearExpiry(2023);
		ccDetails.setCreatedBy(user);
		creditCardDetailsRepository.save(ccDetails);
		
		ccDetails2 = new CreditCardDetails();
		ccDetails2.setCardNumber("12315-51882-0932");
		ccDetails2.setHoldersName("RYAN KRISTOFFER BARTOLAY");
		ccDetails2.setMonthExpiry(2);
		ccDetails2.setYearExpiry(2023);
		ccDetails2.setCreatedBy(user);
		creditCardDetailsRepository.save(ccDetails2);
		
		
		createSalesPerson();
		createSalesInvoices();
	}

	private void createSalesPerson() {
		sales1 = new User();
		sales1.setUsername("sales1");
		sales1.setPassword(passwordEncoder.encode(DevBootstrap.PASSWORD));
		sales1.setFirstName("Sales");
		sales1.setLastName("Sales");
		sales1.setAccountType(AccountType.USER);
		sales1.setEmail("sales@gmail.com");
		sales1.setAuthority("");
		userRepository.save(sales1);
		
		sales2 = new User();
		sales2.setUsername("sales2");
		sales2.setPassword(passwordEncoder.encode(DevBootstrap.PASSWORD));
		sales2.setFirstName("Sales2");
		sales2.setLastName("Sales2");
		sales2.setAccountType(AccountType.USER);
		sales2.setEmail("sales2@gmail.com");
		sales2.setAuthority("");
		userRepository.save(sales2);
	}

	private void createSalesInvoices() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 4, 21, 22, 33, 5);
		
		SalesInvoiceForm salesForm = new SalesInvoiceForm();
		salesForm.setPaymentMethod(PaymentMethod.CREDITCARD);
		salesForm.setCreditCardDetails(ccDetails);
		salesForm.setDocumentNumber("Sales#12344");
		salesForm.setYear("2018");
		salesForm.setTransactionDate(cal.getTime());
		salesForm.setSalesPerson(sales1);
		salesForm.setCreatedBy(user);
		salesForm.setLocation(locationRepository.findById(1).get());
		
		salesInvoiceService.create(salesForm);
		
		cal.set(2018, 5, 21, 22, 33, 5);
		
		SalesInvoiceForm salesForm2 = new SalesInvoiceForm();
		salesForm2.setPaymentMethod(PaymentMethod.CASH);
		salesForm2.setDocumentNumber("Sales#12aas34");
		salesForm2.setYear("2018");
		salesForm2.setTransactionDate(cal.getTime());
		salesForm2.setSalesPerson(sales2);
		salesForm2.setCreatedBy(user);
		salesForm2.setLocation(locationRepository.findById(2).get());
		
		salesInvoiceService.create(salesForm2);
		
	}

}
