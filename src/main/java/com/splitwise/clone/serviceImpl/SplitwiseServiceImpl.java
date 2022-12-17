package com.splitwise.clone.serviceImpl;

import com.splitwise.clone.entity.PersonEntity;
import com.splitwise.clone.model.Person;
import com.splitwise.clone.repository.SplitwiseRepository;
import com.splitwise.clone.service.SplitwiseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SplitwiseServiceImpl implements SplitwiseService {
	
	@Autowired
	private SplitwiseRepository repository;

	@Override
	public Person insertPersonDetails(Person person) {
		PersonEntity personEntity = new PersonEntity();
		BeanUtils.copyProperties(person, personEntity);
		repository.save(personEntity);
		person.setPersonId(personEntity.getPersonId());
		return person;
	}

}
