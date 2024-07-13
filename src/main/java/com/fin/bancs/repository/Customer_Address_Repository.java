package com.fin.bancs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.customer.Cust_Address_detailsPk;
import com.fin.bancs.customer.Customer_Address_Details;

@Repository
@Transactional
public interface Customer_Address_Repository extends JpaRepository<Customer_Address_Details, Cust_Address_detailsPk>{

}
