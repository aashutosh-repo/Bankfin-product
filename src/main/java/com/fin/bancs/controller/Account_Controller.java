package com.fin.bancs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.account.Account;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.services.Account_services;

@RestController
@RequestMapping("/account")
public class Account_Controller {
	@Autowired
	public Account_repository acc_repo;
	@Autowired
	public Account_services acc_services;
	
    @GetMapping("/viewAccounts")
    public List<Account> viewAccountDetails(Model theModel){    

    	List<Account> allAccount =acc_repo.findAll();
    	return allAccount;
    }
    
    @PostMapping("/create")
    public void createAccount(@RequestBody Account account) {
    	acc_services.ceateModifyAccountDetails(account, 0);
    }
    
    @PostMapping("/modify")
    public ResponseEntity<Account> modifyAccount(@RequestBody Account account) {
    	Account accountOut = acc_services.ceateModifyAccountDetails(account, 1);
    	return ResponseEntity.ok(accountOut);
    }
}
