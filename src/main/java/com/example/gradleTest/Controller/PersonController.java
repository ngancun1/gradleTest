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
import com.example.gradleTest.model.PhoneNumber;
import com.example.gradleTest.model.PhoneNumberRequest;

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
	
	@RequestMapping(value = "/Edit", method = RequestMethod.POST)
	public String editPerson(@ModelAttribute(value="person1") PersonRequest person) {
		personService.update(person.getId(), person.getName());
		return "redirect:All";
	}
	
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String deletePerson(@ModelAttribute(value="person1") PersonRequest person){
		personService.delete(person.getId());
		return "redirect:All";
	}
	
	@RequestMapping(value = "/toEditPhoneNumber", method = RequestMethod.GET)
	public String toEditPhoneNumber(Model model, @RequestParam("id") String id) {
		ArrayList<PhoneNumber> tmp = personService.getPhoneNumberList(Long.parseLong(id));
		model.addAttribute("listPhoneNumber",tmp);
		model.addAttribute("personID",id);
		return "/ViewPhoneNumber";
	}
	
	@RequestMapping(value = "/GetOnePerson", method = RequestMethod.GET)
	public @ResponseBody PersonRequest GetOnePerson(@RequestParam("id") String id) {
		long personID = Long.parseLong(id);
		Person tmp = personService.getOneByID(personID);
		PersonRequest personR = new PersonRequest();
		personR.setId(personID);
		personR.setName(tmp.getName());
		return personR;
	}
	
	@RequestMapping(value = "/GetOnePhoneNumber", method = RequestMethod.GET)
	public @ResponseBody PhoneNumberRequest GetOnePhoneNumber(@RequestParam("id") String id) {
		long phoneNumberID = Long.parseLong(id);
		PhoneNumber tmp = personService.getOnePhoneNumberByID(phoneNumberID);
		PhoneNumberRequest phoneR = new PhoneNumberRequest();
		phoneR.setId(phoneNumberID);
		phoneR.setNumber(tmp.getNumber());
		return phoneR;
	}
	
	@RequestMapping(value = "/EditPhoneNumber", method = RequestMethod.POST)
	public String editPerson(@ModelAttribute(value="phoneNumber") PhoneNumberRequest phone) {
		personService.updatePhoneNumber(phone.getId(), phone.getNumber());
		return "redirect:All";
	}
	
	@RequestMapping(value = "/InsertPhoneNumber", method = RequestMethod.POST)
	public String InsertPhoneNumber(@ModelAttribute(value="phoneNumber") PhoneNumberRequest phone) {
		personService.insertPhoneNumber(phone.getPersonID(), phone.getNumber());
		return "redirect:All";
	}
	
	@RequestMapping(value = "/DeletePhoneNumber", method = RequestMethod.POST)
	public String DeletePhoneNumber(@ModelAttribute(value="phoneNumber") PhoneNumberRequest phone) {
		personService.delete(phone.getId());
		return "redirect:All";
	}
	
	@RequestMapping(value = "/Insert",method = RequestMethod.POST)
	public String Insert(@ModelAttribute(value="person") PersonRequest person) {
		personService.insert(person.getName());
		return "redirect:All";
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
