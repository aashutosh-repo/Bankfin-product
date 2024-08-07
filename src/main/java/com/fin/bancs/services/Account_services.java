package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.bancs.account.Account;
import com.fin.bancs.account.AccountPk;
import com.fin.bancs.customer.CustomerID;
import com.fin.bancs.customer.Customer_Details;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.repository.Customer_Details_Repository;
import com.fin.bancs.services.si.Account_Service_Interface;
import com.fin.bancs.utils.SequenceGenerator;

@Service
public class Account_services implements Account_Service_Interface{

	@Autowired
	public Account_repository account_repository;
	@Autowired
	private Customer_Details_Repository custRepository;
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	
	public Account ceateModifyAccountDetails(Account account,int modifyFlag) {
		//If Modify flag is 1 then primary key should pass as a User-input 
		Account acc=  new Account();
		if(modifyFlag==1) {
			//check the given data is existing in DB or not if yes then proceed 
			acc = account_repository.getReferenceById(account.getAccountId());
			if(acc != null) {
				account.setAccountId(acc.getAccountId());
				acc = account_repository.save(account);
			}
			
		} 
		else 
		{
			CustomerID cId = new CustomerID();
			cId.setCustomerID(account.getCust_id());
			cId.setCustomerType(account.getCus_type());
		
			Optional<Customer_Details> cust = custRepository.findById(cId);
			if(cust.isEmpty()) {
				throw new ResourceNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND);
			}
		
			BigInteger entityId = sequenceGenerator.generateSequence("AccountId_seq");
			AccountPk accpk = new AccountPk();
			accpk.setAccount_id(entityId.intValue());
			accpk.setAccount_type(1);
			account.setAccountId(accpk);
			acc= account_repository.save(account);
		}
		return acc;
	}
	
	
	public void deleteAccount(Account account) {
		Account account_del =new Account();
		if(account_repository.findById(account.getAccountId()) != null) {
			account_del = (account_repository.findById(account.getAccountId())).get();
			account_del.setAccount_status(2);
			account_del.setClsr_dt (account.getClsr_dt());
			account_repository.save(account_del);
		}
		
	}
}
