package com.fin.bancs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fin.bancs.common.AccountsConstants;
import com.fin.bancs.dto.CustomerDto;
import com.fin.bancs.dto.ResponseDto;
import com.fin.bancs.mapper.RequestWrapper;
import com.fin.bancs.services.CustomerAddressServices;
import com.fin.bancs.services.CustomerDetailsServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Omega Bank Customer Details Controller",
description = "This Swagger containing CRUD operation for Customer Management")
@RestController
@RequestMapping("/customer")
public class CustomerDtlsController {
    @Autowired
    CustomerDetailsServices customerDetailsServices;
    @Autowired
    CustomerAddressServices custAddServices;



    @Operation(summary = "Find All Customer API",
    description = "REST API to All Customer in Omega Bank")
    @GetMapping("/getaAllCustomerDetails")
    public List<CustomerDto> getAllCustomer(){
        return customerDetailsServices.getAllCust();
    }
    @Operation(summary = "Find Customer By Mobile Number API",
    description = "REST API to Find Customer By Mobile Number in Omega Bank")
    @GetMapping("/getCustByMobileNumber")
    public CustomerDto geCustomerByCustomerId(@RequestParam String mobileNumber){
        return customerDetailsServices.findCustomerByMobileNumber(mobileNumber);
    }
    
    @Operation(summary = "Create Customer API",
    description = "REST API to create Cutomer in Omega Bank")
    @ApiResponse(
            responseCode = "202",
            description = "Http Status CREATED"
    )
    @PostMapping("/customer-Onboarding")
    public ResponseEntity<ResponseDto> creteCust(@RequestBody RequestWrapper requestWrapper) 
	{
    	customerDetailsServices.CreateCustDetails(requestWrapper.getCustomerDto(), 
    			requestWrapper.getDocDto(),requestWrapper.getNomineeDetails());
    	custAddServices.createModifyCustAddressDetails(requestWrapper.getCustomerAddress());
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_202,AccountsConstants.MESSAGE_202));
    }
    
    
    @Operation(summary = "Modify Customer Details API",
    description = "REST API to Modify Customer Details in Omega Bank")
    @ApiResponse(
            responseCode = "201",
            description = "Http Status Succesfully Precessed"
    )
    @PutMapping("/modify-cust") 
    public ResponseEntity<ResponseDto> modifyDetails(@RequestBody CustomerDto customerDetails, 
    		@RequestParam int cust_id, @RequestParam int CustomerType){
    	customerDetailsServices.modifyCustomer(customerDetails, cust_id, CustomerType);
    	
    	return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
    }
    
    
}



