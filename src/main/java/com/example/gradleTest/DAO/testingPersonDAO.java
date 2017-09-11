package com.example.gradleTest.DAO;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.gradleTest.model.Person;
import com.example.gradleTest.model.Person_;
import com.example.gradleTest.model.PhoneNumber;
import com.example.gradleTest.model.PhoneNumber_;

@Repository
public class testingPersonDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	public ArrayList<Person> getAll(){
		CriteriaBuilder personCriteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> personQuery = personCriteriaBuilder.createQuery(Person.class);
		personQuery.from(Person.class);
		TypedQuery<Person> personQ = entityManager.createQuery(personQuery);
		ArrayList<Person> tmp = (ArrayList<Person>) personQ.getResultList();
		for(Person person : tmp) {
			CriteriaBuilder phoneNumberCriteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PhoneNumber> phoneNumberQuery = phoneNumberCriteriaBuilder.createQuery(PhoneNumber.class);
			Root<PhoneNumber> r = phoneNumberQuery.from(PhoneNumber.class);
			phoneNumberQuery.where(phoneNumberCriteriaBuilder.equal(r.get(PhoneNumber_.person), person));
			TypedQuery<PhoneNumber> phoneQ = entityManager.createQuery(phoneNumberQuery);
			person.setPhoneNumber(phoneQ.getResultList());
		}
		return tmp;
	}
	
	public boolean insert(String name) {
		Person tmp = new Person();
		tmp.setName(name);
		try {
			entityManager.persist(tmp);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(long id) {
		try {
			CriteriaBuilder deletePersonCriteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaDelete<Person> personCriteriaDelete = deletePersonCriteriaBuilder.createCriteriaDelete(Person.class);
			Root<Person> r = personCriteriaDelete.from(Person.class);
			personCriteriaDelete.where(deletePersonCriteriaBuilder.equal(r.get(Person_.id), id));
			entityManager.createQuery(personCriteriaDelete).executeUpdate();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean update(long id, String name) {
		try {
			CriteriaBuilder updatePersonCriteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaUpdate<Person> personCriteriaUpdate = updatePersonCriteriaBuilder.createCriteriaUpdate(Person.class);
			Root<Person> r = personCriteriaUpdate.from(Person.class);
			personCriteriaUpdate.where(updatePersonCriteriaBuilder.equal(r.get(Person_.id),id));
			personCriteriaUpdate.set(r.get(Person_.name), name);
			entityManager.createQuery(personCriteriaUpdate).executeUpdate();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public Person getOneByID(long id) {
		CriteriaBuilder getOneByIDCriteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> personQuery = getOneByIDCriteriaBuilder.createQuery(Person.class);
		Root<Person> r = personQuery.from(Person.class);
		personQuery.where(getOneByIDCriteriaBuilder.equal(r.get(Person_.id), id));
		TypedQuery<Person> personQ = entityManager.createQuery(personQuery);
		return personQ.getSingleResult();
	}
	
//	public Person findPersonByName(String name) {
//		CriteriaBuilder findPersonByNameCriteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Person> personQuery = findPersonByNameCriteriaBuilder.createQuery(Person.class);
//		Root<Person> r = personQuery.from(Person.class);
//		personQuery.where(findPersonByNameCriteriaBuilder.equal(r.get(Person_.name), name));
//		TypedQuery<Person> personQ = entityManager.createQuery(personQuery);
//		return personQ.getSingleResult();
//	}
	
}
