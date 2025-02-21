package com.example.localdynamodb.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.localdynamodb.entity.Person;
import com.example.localdynamodb.service.PersonService;

@RestController
@RequestMapping("/curdperson")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping
	public ResponseEntity<String> addPerson(@RequestBody Person person) {
		String result = personService.addPerson(person);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{personId}")
	public ResponseEntity<Person> getPerson(@PathVariable String personId) {
		Person person = personService.getPerson(personId);
		return ResponseEntity.ok(person);
	}
	
	@PutMapping
	public ResponseEntity<String> updatePerson(@RequestBody Person person) {
		String result = personService.updatePerson(person);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{personId}")
	public ResponseEntity<String> deletePerson(@PathVariable String personId) {
		String response = personService.deletePerson(personId);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/tableslist")
	public ResponseEntity<List<String>> printTables() {
		return ResponseEntity.ok(personService.getTables());
	}

}
