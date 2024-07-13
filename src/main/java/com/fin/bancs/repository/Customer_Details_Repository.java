package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.customer.CustomerID;
import com.fin.bancs.customer.Customer_Details;

public interface Customer_Details_Repository extends JpaRepository<Customer_Details, CustomerID>{

}
