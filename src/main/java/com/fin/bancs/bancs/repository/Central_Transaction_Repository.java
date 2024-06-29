package com.fin.bancs.repository;

import com.fin.bancs.transactions.Central_Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public interface Central_Transaction_Repository extends 
						JpaRepository<Central_Transaction, Integer>{

}
