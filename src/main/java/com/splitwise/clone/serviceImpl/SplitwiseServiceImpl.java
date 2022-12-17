package com.splitwise.clone.serviceImpl;

import com.splitwise.clone.entity.PersonEntity;
import com.splitwise.clone.entity.TransactionEntity;
import com.splitwise.clone.model.Person;
import com.splitwise.clone.model.Transaction;
import com.splitwise.clone.repository.PersonRepository;
import com.splitwise.clone.repository.TransactionRepository;
import com.splitwise.clone.service.SplitwiseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class SplitwiseServiceImpl implements SplitwiseService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private TransactionRepository txnRepository;

	@Override
	public Person insertPersonDetails(Person person) {
		PersonEntity personEntity = new PersonEntity();
		BeanUtils.copyProperties(person, personEntity);
		personRepository.save(personEntity);
		person.setPersonId(personEntity.getPersonId());
		return person;
	}

	@Override
	public List<Person> getAllPersonDetails() {
		ArrayList<PersonEntity> personEntityList = (ArrayList<PersonEntity>) personRepository.findAll();
		ArrayList<Person> personList = (ArrayList<Person>) personEntityList.stream()
				.map(e -> new Person(e.getPersonId(), e.getPersonEmail(), e.getPersonName()))
				.collect(Collectors.toList());
		return personList;
	}

	@Override
	public Boolean addTransaction(Transaction transaction) {
		Long amountShare = transaction.getAmount() / transaction.getLoaners().size();
		for (int i = 0; i < transaction.getLoaners().size(); i++) {
			TransactionEntity entity = new TransactionEntity();
			entity.setPersonId(transaction.getLoaners().get(i));
			if (txnRepository.findByPersonId(transaction.getLoaners().get(i))!=null) {
				entity =txnRepository.findByPersonId(transaction.getLoaners().get(i));				
				if (transaction.getPersonId() == entity.getPersonId()) {
					entity.setAmount(entity.getAmount() + transaction.getAmount() - amountShare);
					txnRepository.save(entity);
				} else {
					entity.setAmount(entity.getAmount() - amountShare);
					txnRepository.save(entity);
				}
			} else {
				if (transaction.getPersonId() == entity.getPersonId()) {
					entity.setAmount(transaction.getAmount() - amountShare);
					txnRepository.save(entity);
				} else {
					entity.setAmount(-amountShare);
					txnRepository.save(entity);
				}
			}
		}
		return true;
	}

	@Override
	public List<Transaction> getAllTxnDetails() {
		ArrayList<TransactionEntity> txnEntityList = (ArrayList<TransactionEntity>) txnRepository.findAll();
		ArrayList<Transaction> txnList = (ArrayList<Transaction>) txnEntityList.stream()
				.map(e -> new Transaction(e.getTxnId(), e.getPersonId(), e.getAmount(), null))
				.collect(Collectors.toList());
		return txnList;
	}

}
