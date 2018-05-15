package com.bartolay.inventory.development;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.services.InventoryService;

@Component
@Transactional
public class SalesCancelInvoiceBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private InventoryService inventoryService;
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		SalesInvoice salesInvoice = new SalesInvoice();
		salesInvoice.setSystemNumber("20181-1");
		
		inventoryService.cancelSalesInvoice(salesInvoice);
	}

}
