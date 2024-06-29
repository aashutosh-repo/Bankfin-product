package com.fin.bancs.repository;

import com.fin.bancs.transactions.Core_Transaction_Layer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public interface Core_Transaction_Layer_Repository extends 
						JpaRepository<Core_Transaction_Layer, Integer>{

}
