package com.splitwise.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.splitwise.clone.entity.PersonEntity;

@Repository
public interface SplitwiseRepository extends JpaRepository<PersonEntity, Long> {

}
