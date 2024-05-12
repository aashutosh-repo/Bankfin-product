package com.fin.bancs.repository;

import com.fin.bancs.BP.Cust_Address_detailsPk;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.BP.Customer_Address_Details;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface Customer_Address_Repository extends JpaRepository<Customer_Address_Details, Cust_Address_detailsPk>{

}
