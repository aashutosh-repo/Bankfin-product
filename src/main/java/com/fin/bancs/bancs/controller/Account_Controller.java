package com.fin.bancs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fin.bancs.AM.Account;
import com.fin.bancs.repository.Account_repository;

@Repository
public class Account_Controller {

	@Autowired
	public Account_repository account_repository;
	
	
	public void ceateAccountDetails(Account account) {
		
	account_repository.save(account);
	}
}
