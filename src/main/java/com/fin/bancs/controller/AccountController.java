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

import com.fin.bancs.account.Account;
import com.fin.bancs.common.AccountsConstants;
import com.fin.bancs.dto.AccountDto;
import com.fin.bancs.dto.ResponseDto;
import com.fin.bancs.services.Account_services;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Omega Bank Account Controller",
description = "This Swagger containing CRUD operation for Account Management")
@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	public Account_services acc_services;
	
    @Operation(summary = "View All Accounts API",
    description = "REST API to View All account in MyBank")
    @GetMapping("/viewAccounts")
    public List<AccountDto> viewAccountDetails(){    
    	List<AccountDto> allAccount =acc_services.findAllAccounts();
    	return allAccount;
    }
    @Operation(summary = "Create Account API",
    description = "REST API to create account in Omega Bank")
    @ApiResponse(
            responseCode = "201",
            description = "Http Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody AccountDto account,@RequestParam String MobileNum) {
    	System.out.println(account.toString());
    	acc_services.ceateModifyAccountDetails(account, 0);
    	return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }
    
    @Operation(summary = "Modify Account API",
    description = "REST API to Modify Details in Omega Bank")
    @ApiResponse(
            responseCode = "201",
            description = "Http Status Succesfully Precessed"
    )
    @PutMapping("/modify")
    public ResponseEntity<ResponseDto> modifyAccount(@RequestBody AccountDto account) {
    	Account accountOut = acc_services.ceateModifyAccountDetails(account, 1);
    	return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
    }
}
