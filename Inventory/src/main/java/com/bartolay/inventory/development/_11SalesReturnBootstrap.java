package com.bartolay.inventory.development;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.form.SalesReturnForm;

@Component
public class _11SalesReturnBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Override
	public int getOrder() {
		return 11;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		
		SalesReturnForm salesReturnForm = new SalesReturnForm();
		
	}

}
