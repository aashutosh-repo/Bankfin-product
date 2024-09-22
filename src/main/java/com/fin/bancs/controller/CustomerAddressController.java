package com.fin.bancs.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fin.bancs.customer.AddressID;
import com.fin.bancs.customer.CustomerAddressDetails;
import com.fin.bancs.dto.CustomerAddressDto;
import com.fin.bancs.services.CustomerAddressServices;

@Tag(name = "Omega Bank Address Controller",
		description = "This Swagger containing CRUD operation for Address Management")
@RestController
@RequestMapping("/cust-address")
public class CustomerAddressController {
	
	@Autowired
	private CustomerAddressServices cusServices;
	
	@PostMapping("/create")
	public void createAddressDetails(@RequestBody CustomerAddressDto custAddress ) {
		cusServices.createModifyCustAddressDetails(custAddress);
	}
	
	@PutMapping("/modify")
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
