package com.fin.bancs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fin.bancs.AM.Account;
import com.fin.bancs.repository.Account_repository;

@Repository
public class Account_services {

	@Autowired
	public Account_repository account_repository;
	
	
	public void ceateAccountDetails(Account account) {
		
	account_repository.save(account);
	}
}
