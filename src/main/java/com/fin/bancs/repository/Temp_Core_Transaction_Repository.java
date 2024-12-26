package com.fin.bancs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.transactions.Temp_Core_Transaction;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface Temp_Core_Transaction_Repository extends JpaRepository<Temp_Core_Transaction, Integer>{

}
