package com.fin.bancs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.customer.Documents_Details;

@Repository
@Transactional
public interface Documents_Repository extends JpaRepository<Documents_Details, Integer> {

}
