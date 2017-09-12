package com.example.gradleTest.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String>{
	


	@Override
	public void initialize(Name constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return false;
		}
		else if(value.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return false;
		else return true;
	}

}
