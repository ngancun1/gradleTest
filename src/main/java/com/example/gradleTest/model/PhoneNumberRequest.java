package com.example.gradleTest.model;

public class PhoneNumberRequest {
	private long id;
	private String number;
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
