package com.fin.bancs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.transactions.Core_Transaction;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface Core_Transaction_Repository extends 
						JpaRepository<Core_Transaction, Integer>{
	
	List<Core_Transaction>  findByTxnRefId(String txnId);

}
