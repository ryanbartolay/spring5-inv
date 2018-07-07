package com.bartolay.inventory.development;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.exceptions.SalesReturnException;
import com.bartolay.inventory.form.SalesReturnForm;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;
import com.bartolay.inventory.sales.entity.SalesReturnItem;
import com.bartolay.inventory.sales.entity.SalesReturnItemReason;
import com.bartolay.inventory.sales.repositories.SalesInvoiceItemRepository;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;
import com.bartolay.inventory.sales.repositories.SalesReturnItemReasonRepository;
import com.bartolay.inventory.sales.services.SalesReturnService;

@Component
@Transactional
public class _11SalesReturnBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;
	
	@Autowired
	private SalesInvoiceItemRepository salesInvoiceItemRepository;
	
	@Autowired
	private SalesReturnService salesReturnService;
	
	@Autowired
	private SalesReturnItemReasonRepository salesReturnItemReasonRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public int getOrder() {
		return 11;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		createSalesReturnItemReasons();
		
		SalesReturnForm salesReturnForm = new SalesReturnForm();
		
		final SalesInvoice salesInvoice = salesInvoiceRepository.findById("20181-1").get();
		
		System.err.println("sales invoice " + salesInvoice);
		
		List<SalesInvoiceItem> salesInvoiceItems = salesInvoiceItemRepository.findBySalesInvoice(salesInvoice);
		
		System.err.println("xxxxx");
		System.err.println(salesInvoiceItems);
		
		salesReturnForm.setSalesInvoice(salesInvoice);
		
		List<SalesReturnItem> salesReturnItems = new ArrayList<>();
		
		SalesReturnItem item = new SalesReturnItem();
		item.setQuantity(new BigDecimal(0.21));
		item.setSalesInvoiceItem(salesInvoiceItems.get(0));
		item.setSalesReturnItemReason(salesReturnItemReasonRepository.findByCode("DAMAGED"));
		salesReturnItems.add(item);
		
		salesReturnForm.setSalesReturnItems(salesReturnItems);
		try {
			salesReturnService.create(salesReturnForm);
		} catch (SalesReturnException e) {
			e.printStackTrace();
		}
	}

	private void createSalesReturnItemReasons() {
		SalesReturnItemReason reason = new SalesReturnItemReason();
		reason.setCode("damaged");
		reason.setDescription("Damaged in Shipment");
		reason.setCreatedBy(userRepository.findById(1).get());
		
		salesReturnItemReasonRepository.save(reason);
		
	}

}
