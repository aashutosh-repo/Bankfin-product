package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fin.bancs.customer.AddressID;
import com.fin.bancs.customer.CustomerAddressDetails;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface Customer_Address_Repository extends JpaRepository<CustomerAddressDetails, AddressID>{

}
