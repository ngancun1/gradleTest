package com.example.gradleTest.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradleTest.DAO.PersonDAO;
import com.example.gradleTest.model.Person;

@Service
public class PersonService {
	@Autowired
	private PersonDAO personDAO;
	
	public ArrayList<Person> getAll(){
		return personDAO.getAll();
	}
	
	public boolean insert(String name) {
		return personDAO.insert(name);
	}
	
	public boolean delete(long id) {
		return personDAO.delete(id);
	}
	
	public boolean update(long id, String name) {
		return personDAO.update(id, name);
	}
	
	public Person getOneByID(long id) {
		return personDAO.getOneByID(id);
	}
}
