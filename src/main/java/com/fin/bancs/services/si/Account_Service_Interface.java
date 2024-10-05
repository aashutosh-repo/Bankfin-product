package com.fin.bancs.services.si;

import com.fin.bancs.account.Account;
import com.fin.bancs.dto.AccountDto;

import java.util.List;

public interface Account_Service_Interface {
	
	Account ceateModifyAccountDetails(AccountDto account, int modifyFlag);
	void deleteAccount(Account account);
	List<AccountDto> findAllAccounts();
	List<Account> getAccountByCustomerId(int customerId);

}
