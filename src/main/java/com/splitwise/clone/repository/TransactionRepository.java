package com.splitwise.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.splitwise.clone.entity.TransactionEntity;
import com.splitwise.clone.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>{
	
	TransactionEntity findByPersonId(Long personId);

}
