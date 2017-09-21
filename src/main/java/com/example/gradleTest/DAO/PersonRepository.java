package com.example.gradleTest.DAO;

import org.springframework.data.repository.CrudRepository;
import com.example.gradleTest.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

}
