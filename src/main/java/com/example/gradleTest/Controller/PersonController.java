package com.example.gradleTest.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value = "/Edit", method = RequestMethod.GET)
	public String editPerson(@RequestParam("id") String id,@RequestParam("name") String name) {
		long personID = Long.parseLong(id);
		personService.update(personID, name);
		return "redirect:All";
	}
	
	@RequestMapping(value = "/Delete", method = RequestMethod.GET)
	public String editPerson(@RequestParam("id") String id){
		long personID = Long.parseLong(id);
		personService.delete(personID);
		return "redirect:All";
	}
	
	//Test DAO (khong chay luon)
	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	public String getOnePerson(Model model){
		
		Person person = new Person();
		person = personService.getOneByID(2);
		model.addAttribute("person",person);
		return "/NewFile";
	}
}
