package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.BP.CustomerID;
import com.fin.bancs.BP.Customer_Details;

public interface Customer_Details_Repository extends JpaRepository<Customer_Details, CustomerID>{

}
