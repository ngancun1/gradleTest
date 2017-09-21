package com.example.gradleTest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class PhoneNumberRequest {
	@NotNull
	private long id;
	
	@NotEmpty(message = "Can not be empty")
	@Pattern(regexp = "^[0-9]*$", message = "numeric only")
	private String number;
	
	@NotNull
	private long personID;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public long getPersonID() {
		return personID;
	}
	public void setPersonID(long personID) {
		this.personID = personID;
	}
	public PhoneNumberRequest() {
		super();
	}
	
	
}
