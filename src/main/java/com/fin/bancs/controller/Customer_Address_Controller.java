package com.fin.bancs.controller;

import com.fin.bancs.BP.Customer_Address_Details;
import com.fin.bancs.repository.Customer_Address_Repository;
import com.fin.bancs.repository.Customer_Details_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Customer_Address_Controller {

    @Autowired
    private Customer_Address_Repository customerAddressRepository;
    	public Customer_Address_Details modifyCustAddressDetails(Customer_Address_Details customerAddressDetails){
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

            Customer_Address_Details cust_add_dtls = customerAddressRepository.save(customer_address_details);
            return cust_add_dtls;
	}

}
