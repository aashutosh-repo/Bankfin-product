package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.BP.CustomerID;
import com.fin.bancs.BP.Customer_Details;

import jakarta.transaction.Transactional;

public interface Customer_Details_Repository extends JpaRepository<Customer_Details, CustomerID>{

}
