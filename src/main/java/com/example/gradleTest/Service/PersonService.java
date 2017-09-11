package com.example.gradleTest.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gradleTest.DAO.PersonDAO;
import com.example.gradleTest.DAO.testingPersonDAO;
import com.example.gradleTest.model.Person;
import com.example.gradleTest.model.PhoneNumber;

@Service
public class PersonService {
	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private testingPersonDAO testPersonDAO;
	
	public ArrayList<Person> getAll(){
		return testPersonDAO.getAll();
	}
	
	@Transactional
	public boolean insert(String name) {
		return testPersonDAO.insert(name);
	}
	
	@Transactional
	public boolean delete(long id) {
		return testPersonDAO.delete(id);
	}
	
	@Transactional
	public boolean update(long id, String name) {
		return testPersonDAO.update(id, name);
	}
	
	public Person getOneByID(long id) {
		return personDAO.getOneByID(id);
	}
	
	public ArrayList<PhoneNumber> getPhoneNumberList(long id){
		return personDAO.getPhoneNumberList(id);
	}
	
	public PhoneNumber getOnePhoneNumberByID(long id) {
		return personDAO.getOnePhoneNumberByID(id);
	}
	
	public boolean updatePhoneNumber(long id, String number) {
		return personDAO.updatePhoneNumber(id, number);
	}
	
	public boolean insertPhoneNumber(long personID, String number) {
		return personDAO.insertPhoneNumber(personID, number);
	}
	
	public boolean deletePhoneNumber(long id) {
		return personDAO.deletePhoneNumber(id);
	}
}
