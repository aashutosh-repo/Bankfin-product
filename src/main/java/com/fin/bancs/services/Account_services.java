package com.fin.bancs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fin.bancs.account.Account;
import com.fin.bancs.account.AccountPk;
import com.fin.bancs.repository.Account_repository;

@Repository
public class Account_services {

	@Autowired
	public Account_repository account_repository;
	
	
	public void ceateModifyAccountDetails(Account account,int modifyFlag) {
		//If Modify flag is 1 then primary key should pass as a User-input 
		Account acc=  new Account();
		if(modifyFlag==1) {
			AccountPk accpk = new AccountPk();
					
			accpk.setACCOUNT_ID(account.getACCOUNT_ID());
			accpk.setACCOUNT_TYPE(account.getACCOUNT_TYPE());
			//check the given data is existing in DB or not if yes then proceed 
			acc = account_repository.getReferenceById(accpk);
			if(acc != null) {
				account_repository.save(account);
			}
		}
		
		account_repository.save(account);
	}
	
	public void deleteAccount(Account account) {
		Account account_del =new Account();
		AccountPk accountPk = new AccountPk();
		accountPk.setACCOUNT_ID(account.getACCOUNT_ID());
		accountPk.setACCOUNT_TYPE(account.getACCOUNT_TYPE());
		if(account_repository.findById(accountPk) != null) {
			account_del = (account_repository.findById(accountPk)).get();
			account_del.setACCOUNT_STATUS(2);
			account_del.setCLSNG_DATE(account.getCLSNG_DATE());
			account_repository.save(account_del);
		}
		
	}
}
