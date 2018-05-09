package com.bartolay.inventory.development;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.stock.StockOpening;

@Component
public class StockBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Override
	public int getOrder() {
		return 3;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		StockOpening opening1 = new StockOpening();
	}

}
