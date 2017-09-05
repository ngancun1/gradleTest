package com.example.gradleTest.model;

public class PhoneNumberRequest {
	private long id;
	private String number;
	private Person person;
	
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public PhoneNumberRequest() {
		super();
	}
	
	
}
