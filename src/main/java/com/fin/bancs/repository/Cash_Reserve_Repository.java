package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.transactions.Cash_Reserve;

@Repository
public interface Cash_Reserve_Repository extends JpaRepository<Cash_Reserve, Integer>{
	Cash_Reserve findByTransactionId(String txnId);

}
