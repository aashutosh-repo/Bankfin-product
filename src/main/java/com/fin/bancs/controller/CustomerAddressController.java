package com.fin.bancs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.customer.AddressID;
import com.fin.bancs.customer.CustomerAddressDetails;
import com.fin.bancs.dto.CustomerAddressDto;
import com.fin.bancs.services.CustomerAddressServices;

@RestController
@RequestMapping("/cust-address")
public class CustomerAddressController {
	
	@Autowired
	private CustomerAddressServices cusServices;
	
	@GetMapping("/create")
	public void createAddressDetails(@RequestBody CustomerAddressDto custAddress ) {
		cusServices.createModifyCustAddressDetails(custAddress);
	}
	
	@GetMapping("/modify")
	public void modifyAddressDetails(@RequestBody CustomerAddressDto cust_address ) {
		cusServices.createModifyCustAddressDetails(cust_address);
	}
	
	@GetMapping("/delete")
	public void deleteAddressDetails(@RequestBody CustomerAddressDto cust_address ) {
//		int add_id= cust_address.getADDRESS_ID();
		AddressID addresspk = new AddressID();
//		addresspk.setPIN_CODE(cust_address.getADDRESS_ID());
//		addresspk.setPIN_CODE(cust_address.getPIN_CODE());
		
		
	}

}
