package com.fin.bancs.mapper;

import com.fin.bancs.account.Account;
import com.fin.bancs.dto.AccountDto;

public class AccountMapper {
	public static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {
		accountDto.setAccount_status(account.getAccount_status());
        accountDto.setAccount_number(account.getAccountNumber());
        accountDto.setAccount_open_dt(account.getAccount_open_dt());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setCust_id(account.getCustId());
        accountDto.setCus_type(account.getCus_type());
        accountDto.setNpa_status(account.getNpa_status());
        accountDto.setMin_bal(account.getMin_bal());
        accountDto.setAvailable_balance(account.getAvailable_balance());
        accountDto.setOwner_name(account.getOwner_name());
        accountDto.setAtm_req_flag(account.getAtm_req_flag());
        accountDto.setCheq_req_flag(account.getCheq_req_flag());
        accountDto.setSms_req_flag(account.getSms_req_flag());
        accountDto.setClsr_dt(account.getClsr_dt());
        accountDto.setClsr_reason(account.getClsr_reason());
		
        return accountDto;
	}
	
	public static Account mapToAccount(AccountDto accountDto, Account account ) {
		account.setAccount_status(accountDto.getAccount_status());
        account.setAccountNumber(accountDto.getAccount_number());
        account.setAccount_open_dt(accountDto.getAccount_open_dt());
        account.setCurrency(accountDto.getCurrency());
        account.setCustId(accountDto.getCust_id());
        account.setCus_type(accountDto.getCus_type());
        account.setNpa_status(accountDto.getNpa_status());
        account.setMin_bal(accountDto.getMin_bal());
        account.setAvailable_balance(accountDto.getAvailable_balance());
        account.setOwner_name(accountDto.getOwner_name());
        account.setAtm_req_flag(accountDto.getAtm_req_flag());
        account.setCheq_req_flag(accountDto.getCheq_req_flag());
        account.setSms_req_flag(accountDto.getSms_req_flag());
        account.setClsr_dt(accountDto.getClsr_dt());
        account.setClsr_reason(accountDto.getClsr_reason());
		
        return account;
	}

}
