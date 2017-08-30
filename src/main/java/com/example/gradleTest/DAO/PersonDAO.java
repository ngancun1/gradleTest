package com.example.gradleTest.DAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.gradleTest.Utils.HibernateUtil;
import com.example.gradleTest.model.Person;

@Repository
public class PersonDAO {
	public ArrayList<Person> getAll(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from person");
			ArrayList<Person> listPerson = (ArrayList<Person>) query.list();
			return listPerson;
		}catch(Exception ex) {
			transaction.rollback();
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
			transaction.rollback();
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
				transaction.rollback();
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
				transaction.rollback();
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
			Query query = session.createQuery("from person where id = :id");
			query.setParameter("id", id);
			Person person = (Person) query.uniqueResult();
			return person;
		}catch(Exception ex) {
			transaction.rollback();
		}finally {
			session.close();
		}
		return null;
	}
}
