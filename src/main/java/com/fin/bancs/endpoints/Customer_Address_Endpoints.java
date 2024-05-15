package com.fin.bancs.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.bancs.BP.Cust_Address_detailsPk;
import com.fin.bancs.BP.Customer_Address_Details;
import com.fin.bancs.services.Customer_Address_services;

@Controller
@RequestMapping("/cust-address")
public class Customer_Address_Endpoints {
	
	@Autowired
	private Customer_Address_services cusServices;
	
	@GetMapping("/create")
	public void createAddressDetails(@RequestBody Customer_Address_Details cust_address ) {
		cusServices.createModifyCustAddressDetails(cust_address);
	}
	
	@GetMapping("/modify")
	public void modifyAddressDetails(@RequestBody Customer_Address_Details cust_address ) {
		cusServices.createModifyCustAddressDetails(cust_address);
	}
	
	@GetMapping("/delete")
	public void deleteAddressDetails(@RequestBody Customer_Address_Details cust_address ) {
		int add_id= cust_address.getADDRESS_ID();
		Cust_Address_detailsPk addresspk = new Cust_Address_detailsPk();
		addresspk.setPIN_CODE(cust_address.getADDRESS_ID());
		addresspk.setPIN_CODE(cust_address.getPIN_CODE());
		if(cusServices.findCustAddressByID(addresspk) != null) {
			cusServices.deleteAddress(cust_address);
		}else {
			//Handle Address Not Found Error
		}
		
	}

}
