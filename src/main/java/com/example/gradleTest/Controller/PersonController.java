package com.example.gradleTest.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gradleTest.Service.PersonCRUDService;
import com.example.gradleTest.Service.PersonService;
import com.example.gradleTest.model.Person;
import com.example.gradleTest.model.PersonRequest;
import com.example.gradleTest.model.PhoneNumber;
import com.example.gradleTest.model.PhoneNumberRequest;

@Controller
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@Autowired
	PersonCRUDService personCRUDService;
	
	@RequestMapping(value = {"", "/", "/All"}, method = RequestMethod.GET)
	public String getAllPerson(Model model) {
		List<Person> listPerson = personCRUDService.getAll();
		model.addAttribute("listPerson",listPerson);
		return "/getAll";
	}
	
	@RequestMapping(value = "/Edit", method = RequestMethod.POST)
	public String editPerson(@ModelAttribute(value="person1") PersonRequest person) {
		personCRUDService.update(person);
		return "redirect:All";
	}
	
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String deletePerson(@ModelAttribute(value="person1") PersonRequest person){
		personCRUDService.delete(person);
		return "redirect:All";
	}
	
	@RequestMapping(value = "/toEditPhoneNumber", method = RequestMethod.GET)
	public String toEditPhoneNumber(Model model, @RequestParam("id") String id) {
		ArrayList<PhoneNumber> tmp = personService.getPhoneNumberList(Long.parseLong(id));
		model.addAttribute("listPhoneNumber",tmp);
		model.addAttribute("personID",id);
		return "/ViewPhoneNumber";
	}
	
	@RequestMapping(value = "/GetOnePerson", method = RequestMethod.POST)
	public @ResponseBody PersonRequest GetOnePerson(@RequestParam("id") String id) {
		long personID = Long.parseLong(id);
		Person tmp = personService.getOneByID(personID);
		PersonRequest personR = new PersonRequest();
		personR.setId(personID);
		personR.setName(tmp.getName());
		return personR;
	}
	
	@RequestMapping(value = "/GetOnePhoneNumber", method = RequestMethod.POST)
	public @ResponseBody PhoneNumberRequest GetOnePhoneNumber(@RequestParam("id") String id) {
		long phoneNumberID = Long.parseLong(id);
		PhoneNumber tmp = personService.getOnePhoneNumberByID(phoneNumberID);
		PhoneNumberRequest phoneR = new PhoneNumberRequest();
		phoneR.setId(phoneNumberID);
		phoneR.setNumber(tmp.getNumber());
		return phoneR;
	}
	
	@RequestMapping(value = "/EditPhoneNumber", method = RequestMethod.POST)
	public String editPerson(@ModelAttribute(value="phoneNumber") PhoneNumberRequest phone, RedirectAttributes redirectAttributes) {
		personService.updatePhoneNumber(phone.getId(), phone.getNumber());
		redirectAttributes.addAttribute("id", phone.getPersonID());
		return "redirect:toEditPhoneNumber";
	}
	
	@RequestMapping(value = "/InsertPhoneNumber", method = RequestMethod.POST)
	public String InsertPhoneNumber(@ModelAttribute(value="phoneNumber") PhoneNumberRequest phone, RedirectAttributes redirectAttributes) {
		personService.insertPhoneNumber(phone.getPersonID(), phone.getNumber());
		redirectAttributes.addAttribute("id", phone.getPersonID());
		return "redirect:toEditPhoneNumber";
	}
	
	@RequestMapping(value = "/DeletePhoneNumber", method = RequestMethod.POST)
	public String DeletePhoneNumber(@ModelAttribute(value="phoneNumber") PhoneNumberRequest phone, RedirectAttributes redirectAttributes) {
		personService.deletePhoneNumber(phone.getId());
		redirectAttributes.addAttribute("id", phone.getPersonID());
		return "redirect:toEditPhoneNumber";
	}
	
	@RequestMapping(value = "/Insert",method = RequestMethod.POST)
	public String Insert(@Validated @ModelAttribute(value="person") PersonRequest person, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		else {
			personCRUDService.insert(person);
			return "redirect:All";
		}
	}
	
	@RequestMapping(value = "/ValidateEditPerson",method = RequestMethod.POST)
	public @ResponseBody String ValidatePerson(@Validated @RequestBody PersonRequest person, BindingResult result) {
		if(result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors ) {
		        errorMessage.append(error.getObjectName() + " - " + error.getDefaultMessage());
		    }
			return errorMessage.toString();
		}
		else return "SUCCESS";
	}
	
	@RequestMapping(value = "/ValidateEditPhoneNumber",method = RequestMethod.POST)
	public @ResponseBody String ValidatePhoneNumber(@Validated @RequestBody PhoneNumberRequest phoneNumber, BindingResult result) {
		if(result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors ) {
		        errorMessage.append(error.getObjectName() + " - " + error.getDefaultMessage());
		    }
			return errorMessage.toString();
		}
		else return "SUCCESS";
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
