package com.bartolay.inventory.development;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class _12StockAdjustment implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered{

	
	
	@Override
	public int getOrder() {
		return 12;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
