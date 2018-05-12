package com.bartolay.inventory.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.AccountType;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;

@Component
public class SaleBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered  {

	@Autowired
	private CreditCardDetailsRepository creditCardDetailsRepository;
	
	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;
	
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
		
		SalesInvoice salesInvoice = new SalesInvoice();
		salesInvoice.setPaymentMethod(PaymentMethod.CREDITCARD);
		salesInvoice.setCreditCardDetails(ccDetails);
		salesInvoice.setDocumentNumber("Sales#12344");
		salesInvoice.setSalesPerson(sales1);
		salesInvoice.setCreatedBy(user);
		
		salesInvoiceRepository.save(salesInvoice);
		
		SalesInvoice salesInvoice2 = new SalesInvoice();
		salesInvoice2.setPaymentMethod(PaymentMethod.CREDITCARD);
		salesInvoice2.setDocumentNumber("Sales#12344");
		salesInvoice2.setSalesPerson(sales1);
		salesInvoice2.setCreatedBy(user);
		
		salesInvoiceRepository.save(salesInvoice2);
	}

}
