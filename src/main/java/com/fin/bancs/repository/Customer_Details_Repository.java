package com.fin.bancs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.customer.CustomerID;
import com.fin.bancs.customer.CustomerDetails;

public interface Customer_Details_Repository extends JpaRepository<CustomerDetails, CustomerID>{
	Optional<CustomerDetails> findByMobileNumber(String mobNumber);
}
