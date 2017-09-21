package com.example.gradleTest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.gradleTest.Validator.Name;

public class PersonRequest {
	
	@NotNull
	private long id;
	
	@NotEmpty(message = "Can not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$", message = "alphabetical characters only")
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PersonRequest() {
		super();
	}
}
