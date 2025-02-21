package com.example.localdynamodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.localdynamodb.entity.Person;
import com.example.localdynamodb.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
    private PersonRepository personRepository;
	
	public String addPerson(Person person) {
		personRepository.save(person);
		return person.getName() +" is added successfully";
	}
	
	public Person getPerson(String personId) {
		return personRepository.findById(personId);
	}
	
	public String updatePerson(Person person) {
		personRepository.save(person);
		return person.getName() +" is updated successfully";
	}
	
	public String deletePerson(String personId) {
		personRepository.deleteById(personId);
		return personId +" is deleted successfully";
	}
	
	public List<String> getTables() {
		return personRepository.getTables();
	}
}
