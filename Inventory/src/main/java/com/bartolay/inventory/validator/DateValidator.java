package com.bartolay.inventory.validator;

import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bartolay.inventory.validator.model.Date;

public class DateValidator implements ConstraintValidator<Date, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		try {
			java.util.Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(value);  
		    return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
