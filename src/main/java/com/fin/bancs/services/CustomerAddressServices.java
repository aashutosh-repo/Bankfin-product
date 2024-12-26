package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.fin.bancs.customer.AddressID;
import com.fin.bancs.customer.CustomerAddressDetails;
import com.fin.bancs.dto.CustomerAddressDto;
import com.fin.bancs.mapper.AddressMapper;
import com.fin.bancs.repository.Customer_Address_Repository;
import com.fin.bancs.services.si.Address_Service_Interface;
import com.fin.bancs.utils.SequenceGenerator;

@Service
@AllArgsConstructor
public class CustomerAddressServices implements Address_Service_Interface{

    private Customer_Address_Repository customerAddressRepository;
    private SequenceGenerator sequenceGenerator;
    
    	public void createModifyCustAddressDetails(CustomerAddressDto customerAddressDto){
            new CustomerAddressDetails();
            CustomerAddressDetails customer_address_details;
            AddressID addressKey = new AddressID();
            customer_address_details = AddressMapper.mapToCustomerAddress(customerAddressDto, new CustomerAddressDetails());
            BigInteger addressId = sequenceGenerator.generateSequence("AddressId_seq");
            addressKey.setCustomerId(customerAddressDto.getCustomerID());
            addressKey.setAddressId(addressId.intValue());
            customer_address_details.setAddressId(addressKey);
            customerAddressRepository.save(customer_address_details);
	}
    	
    	public Optional<CustomerAddressDetails> findCustAddressByID(AddressID addressID) {
    		return customerAddressRepository.findById(addressID);
    	}
    	public boolean deleteAddress(CustomerAddressDetails customer_Address_Details) {
    		customerAddressRepository.delete(customer_Address_Details);
    		return true;
    	}

}
