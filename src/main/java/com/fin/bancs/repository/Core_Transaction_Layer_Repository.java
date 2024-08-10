package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fin.bancs.transactions.Core_Transaction_Layer;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface Core_Transaction_Layer_Repository extends 
						JpaRepository<Core_Transaction_Layer, Integer>{

}
