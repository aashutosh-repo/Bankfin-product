package com.fin.bancs.services;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fin.bancs.constants.AccountsConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

	private static final Logger logger = LogManager.getLogger(Account_services.class);


	@Autowired
	public Account_repository account_repository;
	@Autowired
	private Customer_Details_Repository custRepository;
	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Override
	@CachePut(value = "accounts", key = "#account.account_number")
	public Account ceateModifyAccountDetails(AccountDto account,int modifyFlag){
        logger.info("Entered int Creation Task of class : {}On : {}", this.getClass().getSimpleName(), LocalDate.now());
		//If Modify flag is 1 then primary key should pass as a User-input
		Account acc;
		Account accCreate;
		if(modifyFlag==1) {
			acc = account_repository.findByAccountNumber(account.getAccount_number());
			//check the given data is existing in DB or not if yes then proceed
			acc.setCurrency(account.getCurrency());
			acc.setOwner_name(account.getOwner_name());
			acc.setAccount_status(account.getAccount_status());
			acc.setClsr_dt(account.getClsr_dt());
			acc.setClsr_reason(acc.getClsr_reason());
            account_repository.save(acc);

        }
		else
		{
			CustomerID cId = new CustomerID();
			cId.setCustomerID(account.getCust_id());
			cId.setCustomerType(account.getCus_type());

			Optional<CustomerDetails> customer = custRepository.findById(cId);
			if(customer.isEmpty()) {
				throw new ResourceNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND);
			}

			BigInteger entityId = sequenceGenerator.generateSequence("AccountId_seq");
			BigInteger intAccNumber = sequenceGenerator.generateSequence("InternalAccNO_seq");
			BigInteger customerAccNumber = sequenceGenerator.generateSequence("CustomerAccNO_seq");
			String intAccNumStr = "OMEGA"+intAccNumber.toString();
			String customerAccountNum = "SB"+ customerAccNumber.toString();
			AccountPk accId = new AccountPk();
			accId.setAccount_id(entityId.intValue());
			accId.setAccount_type(AccountsConstants.SAVINGS_ACCOUNT);
			accCreate= AccountMapper.mapToAccount(account, new Account());
			accCreate.setClsr_dt(null);
			accCreate.setClsr_reason(null);
			accCreate.setAccountId(accId);
			accCreate.setInternalAcntNumber(intAccNumStr);
			accCreate.setAccountNumber(customerAccountNum);

			acc= account_repository.save(accCreate);
            logger.debug("Account Creation Completed :  {}On : {}", this.getClass().getSimpleName(), LocalDate.now());

		}
		return acc;
	}

	@Override
	@CacheEvict(value = "accounts", key = "#account.accountId")
	public void deleteAccount(Account account) {
        new Account();
        Account account_del;
		if(account_repository.findById(account.getAccountId()).isPresent()) {
			account_del = (account_repository.findById(account.getAccountId())).get();
			account_del.setAccount_status(2);
			account_del.setClsr_dt (account.getClsr_dt());
			account_repository.save(account_del);
		}

	}

	@Override
	@Cacheable(value = "accounts")
	public List<AccountDto> findAllAccounts() {
        logger.debug("Account Finding in :  {}On : {}", this.getClass().getSimpleName(), LocalDate.now());

		List<Account> account;
		List<AccountDto> accDto = new ArrayList<AccountDto>();
		AccountDto accountDto = new AccountDto();
		account = account_repository.findAll();
		for(Account acc: account) {
			accountDto.setAccount_number(acc.getAccountNumber());
		}
        logger.debug("Account Finding completed in :  {}On : {}", this.getClass().getSimpleName(), LocalDate.now());

		return accDto;
	}

	@Override
	@Cacheable(value = "accounts", key = "#customerId")
	public List<Account> getAccountByCustomerId(int customerId){
		List<Account> accounts;
		accounts= account_repository.findByCustId(customerId);
		return accounts;
	}
}