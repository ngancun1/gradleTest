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
public class PersonDAO {
	
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
	
	public ArrayList<PhoneNumber> getPhoneNumberList(long id){
		try{
			Person person = getOneByID(id);
			CriteriaBuilder phoneNumberCriteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PhoneNumber> phoneNumberQuery = phoneNumberCriteriaBuilder.createQuery(PhoneNumber.class);
			Root<PhoneNumber> r = phoneNumberQuery.from(PhoneNumber.class);
			phoneNumberQuery.where(phoneNumberCriteriaBuilder.equal(r.get(PhoneNumber_.person), person));
			TypedQuery<PhoneNumber> phoneQ = entityManager.createQuery(phoneNumberQuery);
			return (ArrayList<PhoneNumber>) phoneQ.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public PhoneNumber getOnePhoneNumberByID(long id) {
		CriteriaBuilder getOnePhoneNumberByIDCriteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PhoneNumber> phoneNumberQuery = getOnePhoneNumberByIDCriteriaBuilder.createQuery(PhoneNumber.class);
		Root<PhoneNumber> r = phoneNumberQuery.from(PhoneNumber.class);
		phoneNumberQuery.where(getOnePhoneNumberByIDCriteriaBuilder.equal(r.get(PhoneNumber_.id), id));
		TypedQuery<PhoneNumber> phoneNumberQ = entityManager.createQuery(phoneNumberQuery);
		return phoneNumberQ.getSingleResult();
	}
	
	public boolean updatePhoneNumber(long id, String number) {
		try {
			CriteriaBuilder updatePhoneNumberCriteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaUpdate<PhoneNumber> phoneNumberCriteriaUpdate = updatePhoneNumberCriteriaBuilder.createCriteriaUpdate(PhoneNumber.class);
			Root<PhoneNumber> r = phoneNumberCriteriaUpdate.from(PhoneNumber.class);
			phoneNumberCriteriaUpdate.where(updatePhoneNumberCriteriaBuilder.equal(r.get(PhoneNumber_.id), id));
			phoneNumberCriteriaUpdate.set(r.get(PhoneNumber_.number), number);
			entityManager.createQuery(phoneNumberCriteriaUpdate).executeUpdate();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean insertPhoneNumber(long personID, String number) {
		PhoneNumber tmp = getOnePhoneNumberByID(personID);
		tmp.setNumber(number);
		try {
			entityManager.persist(tmp);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean deletePhoneNumber(long id) {
		try {
			CriteriaBuilder deletePhoneNumberCriteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaDelete<PhoneNumber> phoneNumberCriteriaDelete = deletePhoneNumberCriteriaBuilder.createCriteriaDelete(PhoneNumber.class);
			Root<PhoneNumber> r = phoneNumberCriteriaDelete.from(PhoneNumber.class);
			phoneNumberCriteriaDelete.where(deletePhoneNumberCriteriaBuilder.equal(r.get(PhoneNumber_.id), id));
			entityManager.createQuery(phoneNumberCriteriaDelete).executeUpdate();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
