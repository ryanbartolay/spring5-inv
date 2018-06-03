package com.bartolay.inventory.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class NumericUtility {
	
	DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
	
	public String amount(BigDecimal amount) {
		return formatter.format(amount.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
	}
	public String quantity(BigDecimal quantity) {
		return formatter.format(quantity.setScale(3, BigDecimal.ROUND_HALF_EVEN).doubleValue());
	}
}
