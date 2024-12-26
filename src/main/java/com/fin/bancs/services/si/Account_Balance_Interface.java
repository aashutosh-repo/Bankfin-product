package com.fin.bancs.services.si;

import java.math.BigDecimal;

import com.fin.bancs.account.Account;
import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.transactions.CashTransactionInput;

public interface Account_Balance_Interface {
 	int cashTransactionInitiated(CashTransactionInput txnInput);
	void authCashDeposit(int txn_id,int flag);
	void debitFromAccount(Account accountToCredit, BigDecimal txn_amt);
	void creditToAccount(Account accountToCredit, BigDecimal txn_amt);
	boolean checkTransactionLimit(TransactionDTO txn_input);
}
