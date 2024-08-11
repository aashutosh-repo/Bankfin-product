package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fin.bancs.account.Account;
import com.fin.bancs.account.AccountPk;
import com.fin.bancs.customer.CustomerID;
import com.fin.bancs.customer.CustomerDetails;
import com.fin.bancs.dto.AccountDto;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.mapper.AccountMapper;
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
	
	
	public Account ceateModifyAccountDetails(AccountDto account,int modifyFlag){
		//If Modify flag is 1 then primary key should pass as a User-input 
		Account acc=  new Account();
		Account accCreate=  new Account();
		Account accMdfy=  new Account();
		if(modifyFlag==1) {
			//check the given data is existing in DB or not if yes then proceed 
			//acc = account_repository.getReferenceById(account.getAccountId());
			if(acc != null) {
				accMdfy.setAccountId(acc.getAccountId());
				acc = account_repository.save(accMdfy);
			}
			
		} 
		else 
		{
			CustomerID cId = new CustomerID();
			cId.setCustomerID(account.getCust_id());
			cId.setCustomerType(account.getCus_type());
		
			Optional<CustomerDetails> cust = custRepository.findById(cId);
			if(cust.isEmpty()) {
				throw new ResourceNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND);
			}
		
			BigInteger entityId = sequenceGenerator.generateSequence("AccountId_seq");
			BigInteger intAccNumber = sequenceGenerator.generateSequence("InternalAccNO_seq");
			BigInteger custAccNumber = sequenceGenerator.generateSequence("CustomerAccNO_seq");
			String intAccNumStr = "OMEGA"+intAccNumber.toString();
			String custAccNum = "SB"+ custAccNumber.toString();
			AccountPk accpk = new AccountPk();
			accpk.setAccount_id(entityId.intValue());
			accpk.setAccount_type(1);
			
//	        accCreate.setAvailable_balance(account.getAvailable_balance());
//	        accCreate.setAccount_open_dt(account.getAccount_open_dt());
//	        accCreate.setAccount_status(account.getAccount_status());
//	        accCreate.setOwner_name(account.getOwner_name());
//	        accCreate.setCheq_req_flag(account.getCheq_req_flag());
//	        accCreate.setAtm_req_flag(account.getAtm_req_flag());
//	        accCreate.setClsr_reason(account.getClsr_reason());
//	        accCreate.setClsr_dt(account.getClsr_dt());
//	        accCreate.setCust_id(account.getCust_id());
//	        accCreate.setCus_type(account.getCus_type());
//	       accCreate.setCurrency(account.getCurrency());
			accCreate= AccountMapper.mapToAccount(account, new Account());
			accCreate.setAccountId(accpk);
			accCreate.setIntrnl_acnt_nmbr(intAccNumStr);
			accCreate.setAccount_number(custAccNum);
			
			acc= account_repository.save(accCreate);
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
	
	public List<AccountDto> findAllAccounts() {
		List<Account> account = new ArrayList<>();
		List<AccountDto> accDto = new ArrayList<AccountDto>();
		AccountDto accountDto = new AccountDto();
		account = account_repository.findAll();
		for(Account acc: account) {
			accountDto.setAccount_number(acc.getAccount_number());
		}
		return accDto;
	}
}
