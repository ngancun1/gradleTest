package com.example.gradleTest.model;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="person")
public class Person implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NotEmpty
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "person")
	private List<PhoneNumber> phoneNumber;

	public Person() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PhoneNumber> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	
}
