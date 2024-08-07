package com.fin.bancs.services.si;

import com.fin.bancs.account.Account;

public interface Account_Service_Interface {
	
	Account ceateModifyAccountDetails(Account account,int modifyFlag);
	void deleteAccount(Account account);

}
