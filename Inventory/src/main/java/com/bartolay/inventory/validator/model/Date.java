package com.bartolay.inventory.validator.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bartolay.inventory.validator.DateValidator;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {

    String message() default "Invalid format Date";
     
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
}
