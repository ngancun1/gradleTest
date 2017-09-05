package com.example.gradleTest.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gradleTest.Service.PersonService;
import com.example.gradleTest.model.Person;
import com.example.gradleTest.model.PersonRequest;

@Controller
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "/All", method = RequestMethod.GET)
	public String getAllPerson(Model model) {
		ArrayList<Person> listPerson = personService.getAll();
		model.addAttribute("listPerson",listPerson);
		return "/scrap";
	}
	
	@RequestMapping(value = "/Edit", method = RequestMethod.POST)
	public String editPerson(@ModelAttribute(value="person1") PersonRequest person) {
		personService.update(person.getId(), person.getName());
		return "redirect:All";
	}
	
	@RequestMapping(value = "/Delete", method = RequestMethod.GET)
	public String editPerson(@RequestParam("id") String id){
		long personID = Long.parseLong(id);
		personService.delete(personID);
		return "redirect:All";
	}
	
	@RequestMapping(value = "/toEditPhoneNumber", method = RequestMethod.POST)
	public @ResponseBody String toEditPhoneNumber(Model model, @RequestBody PersonRequest person) {
		System.out.println("something");
		model.addAttribute("listPhoneNumber",personService.getPhoneNumberList(person.getId()));
		return "/EditPhoneNumber";
	}
	
	@RequestMapping(value = "/GetOnePerson", method = RequestMethod.GET)
	public Person GetOnePerson(@RequestParam("id") String id) {
		System.out.println("ran");
		long personID = Long.parseLong(id);
		return personService.getOneByID(personID);
	}
	
	/*
	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	public String getOnePerson(Model model){
		
		Person person = new Person();
		person = personService.getOneByID(2);
		model.addAttribute("person",person);
		return "/NewFile";
	}*/
}
