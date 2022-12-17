package com.splitwise.clone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.clone.model.Person;
import com.splitwise.clone.service.SplitwiseService;

@RestController
@RequestMapping("/api/")
public class SplitwiseController {

	private final SplitwiseService splitwiseService;

	public SplitwiseController(SplitwiseService splitwiseService) {
		this.splitwiseService = splitwiseService;
	}
	
	@PostMapping("/savePersonDetails")
	public Person savePersonDetails(@RequestBody Person person) {
		return splitwiseService.insertPersonDetails(person);
	}
	
	@GetMapping("/getAllPersonDetails")
	public List<Person> getAllPersonDetails() {
		return splitwiseService.getAllPersonDetails();
	}

}
