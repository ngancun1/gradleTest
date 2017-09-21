package com.example.gradleTest.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gradleTest.DAO.PersonRepository;
import com.example.gradleTest.model.Person;
import com.example.gradleTest.model.PersonRequest;

@Service
public class PersonCRUDServiceImpl implements PersonCRUDService{
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public boolean insert(PersonRequest personRequest) {
		try {
			Person person = new Person();
			person.setName(personRequest.getName());
			personRepository.save(person);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean update(PersonRequest personRequest) {
		try {
			Person person = personRepository.findOne(personRequest.getId());
			person.setName(personRequest.getName());
			personRepository.save(person);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delete(PersonRequest personRequest) {
		try {
			Person person = personRepository.findOne(personRequest.getId());
			personRepository.delete(person);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Person> getAll(){
		try {
			return (List<Person>) personRepository.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
