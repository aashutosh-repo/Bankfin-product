package com.fin.bancs.services;

import com.fin.bancs.BP.Cust_Address_detailsPk;
import com.fin.bancs.BP.Customer_Address_Details;
import com.fin.bancs.repository.Customer_Address_Repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Customer_Address_services {

    @Autowired
    private Customer_Address_Repository customerAddressRepository;
    
    	public void createModifyCustAddressDetails(Customer_Address_Details customerAddressDetails){
            Customer_Address_Details customer_address_details= new Customer_Address_Details();

            customer_address_details.setADDRESS_ID(999);
            customer_address_details.setADDRESS_TYPE(customerAddressDetails.getADDRESS_TYPE());
            customer_address_details.setADDRESS_LN1(customerAddressDetails.getADDRESS_LN1());
            customer_address_details.setADDRESS_LN2(customerAddressDetails.getADDRESS_LN2());
            customer_address_details.setCITY(customerAddressDetails.getCITY());
            customer_address_details.setVILLAGE(customerAddressDetails.getVILLAGE());
            customer_address_details.setTALUKA(customerAddressDetails.getTALUKA());
            customer_address_details.setDISTRICT(customerAddressDetails.getDISTRICT());
            customer_address_details.setSTATE(customerAddressDetails.getSTATE());
            customer_address_details.setPIN_CODE(customerAddressDetails.getPIN_CODE());
            customer_address_details.setDATE_OF_CAPTURE(customerAddressDetails.getDATE_OF_CAPTURE());

            customerAddressRepository.save(customer_address_details);
	}
    	
    	public Optional<Customer_Address_Details> findCustAddressByID(Cust_Address_detailsPk Addresspk) {
    		return customerAddressRepository.findById(Addresspk);
    	}
    	public boolean deleteAddress(Customer_Address_Details customer_Address_Details) {
    		customerAddressRepository.delete(customer_Address_Details);
    		return true;
    	}

}
