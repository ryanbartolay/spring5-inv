package com.bartolay.inventory.development;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.exceptions.SalesInvoiceException;
import com.bartolay.inventory.exceptions.SalesReturnException;
import com.bartolay.inventory.form.SalesReturnForm;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;
import com.bartolay.inventory.sales.entity.SalesReturnItem;
import com.bartolay.inventory.sales.repositories.SalesInvoiceItemRepository;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;
import com.bartolay.inventory.sales.repositories.SalesReturnItemRepository;
import com.bartolay.inventory.sales.services.SalesReturnService;

@Component
public class _11SalesReturnBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;
	
	@Autowired
	private SalesInvoiceItemRepository salesInvoiceItemRepository;
	
	@Autowired
	private SalesReturnService salesReturnService;
	
	@Autowired
	private SalesReturnItemRepository salesReturnItemRepository;
	
	@Override
	public int getOrder() {
		return 11;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		
		SalesReturnForm salesReturnForm = new SalesReturnForm();
		
		final SalesInvoice salesInvoice = salesInvoiceRepository.findById("20181-1").get();
		
		System.err.println("sales invoice " + salesInvoice);
		
		List<SalesInvoiceItem> salesInvoiceItems = salesInvoiceItemRepository.findBySalesInvoice(salesInvoice);
		
		System.err.println("xxxxx");
		System.err.println(salesInvoiceItems);
		
		salesReturnForm.setSalesInvoice(salesInvoice);
		
		List<SalesReturnItem> salesReturnItems = new ArrayList<>();
		
		SalesReturnItem item = new SalesReturnItem();
		item.setQuantity(new BigDecimal(1));
		item.setSalesInvoiceItem(salesInvoiceItems.get(0));
		
		salesReturnItems.add(item);
		
		salesReturnForm.setSalesReturnItems(salesReturnItems);
		try {
			salesReturnService.create(salesReturnForm);
		} catch (SalesReturnException e) {
			e.printStackTrace();
		}
	}

}
