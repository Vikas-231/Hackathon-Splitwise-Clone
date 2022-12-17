package com.splitwise.clone.service;

import java.util.List;

import com.splitwise.clone.model.Person;

public interface SplitwiseService {
	
	public Person insertPersonDetails(Person person);
	
	public List<Person> getAllPersonDetails();

}
