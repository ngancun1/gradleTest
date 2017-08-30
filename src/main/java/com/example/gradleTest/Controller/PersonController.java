package com.example.gradleTest.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.gradleTest.Service.PersonService;
import com.example.gradleTest.model.Person;

@Controller
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "/All", method = RequestMethod.GET)
	public String getAllPerson(Model model) {
		ArrayList<Person> listPerson = personService.getAll();
		model.addAttribute("listPerson",listPerson);
		return "/All";
	}
}
