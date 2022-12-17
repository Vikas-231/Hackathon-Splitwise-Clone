package com.splitwise.clone.service;

import java.util.List;

import com.splitwise.clone.model.Person;
import com.splitwise.clone.model.Transaction;

public interface SplitwiseService {
	
	public Person insertPersonDetails(Person person);
	
	public List<Person> getAllPersonDetails();
	
	public Boolean addTransaction(Transaction transaction);
	
	public List<Transaction> getAllTxnDetails();

}
