package com.bartolay.inventory.stock.utils;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.stock.repositories.impl.StockOpeningJdbcRepository;

@Component
public class StockUtility {
	
	@Autowired
	private StockOpeningJdbcRepository stockOpeningJdbcRepository;
	
	public static final String DELIMETER = "-";
	
	/** 
	 * Computes and generates System Number for Stock Opening
	 * SystemNumber Format  YYYY[location_id]-[index] 20182-1
	 * @param location_id
	 * @param year
	 * @return
	 */
	public String generateStockOpeningSystemNumber(int location_id, int year) {
		
		String max_system_number = stockOpeningJdbcRepository.getMaxSystemNumber(location_id, year);
		
		if(max_system_number == null) {
			max_system_number = Calendar.getInstance().get(Calendar.YEAR) + location_id + DELIMETER + 1;
		} else {
			String[] system_number = max_system_number.split(DELIMETER);
			Integer index = Integer.parseInt(system_number[1]);
			index++;
			
			max_system_number = system_number[0] + index;
		}
		
		return max_system_number;
	}
}
