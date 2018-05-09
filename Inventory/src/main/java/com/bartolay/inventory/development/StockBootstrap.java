package com.bartolay.inventory.development;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;

@Component
public class StockBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private StockOpeningRepository stockOpeningRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private User admin;
	
	@Override
	public int getOrder() {
		return 3;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		admin = userRepository.findByUsername("admin");
		
		StockOpening opening1 = new StockOpening();
		opening1.setSystemNumber("2023123-9");
		opening1.setDocumentNumber("XVMill221");
		opening1.setLocation(locationRepository.findById(1).get());
		opening1.setTransactionDate(new Date());
		opening1.setCreatedBy(admin);
		
		stockOpeningRepository.save(opening1);
		
		StockOpening opening2 = new StockOpening();
		opening2.setSystemNumber("2123123-1");
		opening2.setDocumentNumber("35-22-GA2");
		opening2.setLocation(locationRepository.findById(1).get());
		opening2.setTransactionDate(new Date());
		opening2.setCreatedBy(admin);
		
		stockOpeningRepository.save(opening2);
		
		StockOpening opening3 = new StockOpening();
		opening3.setSystemNumber("2023122-1");
		opening3.setDocumentNumber("377MLXzz");
		opening3.setLocation(locationRepository.findById(1).get());
		opening3.setTransactionDate(new Date());
		opening3.setCreatedBy(admin);
		
		stockOpeningRepository.save(opening3);
		
		StockOpening opening4 = new StockOpening();
		opening4.setSystemNumber("2023123-1");
		opening4.setDocumentNumber("DOC2123");
		opening4.setLocation(locationRepository.findById(1).get());
		opening4.setTransactionDate(new Date());
		opening4.setCreatedBy(admin);
		
		stockOpeningRepository.save(opening4);
	}
}
