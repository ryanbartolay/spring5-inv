package com.bartolay.inventory.development;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.services.InventoryCoreService;

@Component
@Transactional
public class _7SalesCancelInvoiceBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private InventoryCoreService inventoryService;
	
	@Override
	public int getOrder() {
		return 7;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		SalesInvoice salesInvoice = new SalesInvoice();
		salesInvoice.setSystemNumber("20181-1");
		
		//inventoryService.cancelSalesInvoice(salesInvoice);
	}
	
}
