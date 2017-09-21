package com.example.gradleTest.Service;

import java.util.List;

import com.example.gradleTest.model.Person;
import com.example.gradleTest.model.PersonRequest;

public interface PersonCRUDService {
	
	boolean insert(PersonRequest personRequest);
	boolean update(PersonRequest personRequest);
	boolean delete(PersonRequest personRequest);
	List<Person> getAll();
}
