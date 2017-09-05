package com.example.gradleTest.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.gradleTest.Utils.HibernateUtil;
import com.example.gradleTest.model.Person;
import com.example.gradleTest.model.PhoneNumber;

@Repository
public class PersonDAO {
	@SuppressWarnings("unchecked")
	public ArrayList<Person> getAll(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Person");
			ArrayList<Person> tmp = (ArrayList<Person>) query.list();
			for(int i = 0;i < tmp.size();i++) {
				Query query1 = session.createQuery("from PhoneNumber where person_id=:person_id");
				query1.setParameter("person_id", tmp.get(i).getId());
				List<PhoneNumber> listPhoneNumber = (List<PhoneNumber>) query1.list();
				tmp.get(i).setPhoneNumber(listPhoneNumber);
			}
			return tmp;
		}catch(Exception ex) {
			if(transaction != null) {
				transaction.rollback();
			}
		}finally {
			session.close();
		}
		return null;
	}
	
	public boolean insert(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Person person = new Person();
			person.setName(name);
			session.save(person);
			return true;
		}catch(Exception ex) {
			if(transaction != null) {
				transaction.rollback();
			}
		}finally {
			session.close();
		}
		return false;
	}
	
	public boolean delete(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Person person = getOneByID(id);
		if(person == null) return false;
		else {
			try {
				transaction = session.beginTransaction();
				session.delete(person);
				transaction.commit();
				return true;
			}catch(Exception ex) {
				if(transaction != null) {
					transaction.rollback();
				}
			}finally {
				session.close();
			}
		}
		return false;
	}
	
	public boolean update(long id, String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Person person = getOneByID(id);
		if(person == null) return false;
		else {
			try {
				transaction = session.beginTransaction();
				person.setName(name);
				session.update(person);
				transaction.commit();
				return true;
			}catch(Exception ex) {
				if(transaction != null) {
					transaction.rollback();
				}
			}finally {
				session.close();
			}
		}
		return false;
	}
	
	public Person getOneByID(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Person where id = :id");
			query.setParameter("id", id);
			Person person = (Person) query.uniqueResult();
			return person;
		}catch(Exception ex) {
			if(transaction != null) {
				transaction.rollback();
			}
		}finally {
			session.close();
		}
		return null;
	}
	
	public ArrayList<PhoneNumber> getPhoneNumberList(long id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from PhoneNumber where person_id = :id");
			query.setParameter("id", id);
			ArrayList<PhoneNumber> tmp = (ArrayList<PhoneNumber>) query.list();
			return tmp;
		}catch(Exception ex) {
			if(transaction != null) {
				transaction.rollback();
			}
		}finally {
			session.close();
		}
		return null;
	}
}
