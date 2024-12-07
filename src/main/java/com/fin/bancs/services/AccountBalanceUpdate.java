package com.fin.bancs.services;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.bancs.account.Account;
import com.fin.bancs.constants.TransactionConstants;
import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.error.CustomErrorMessage;
import com.fin.bancs.repository.Account_repository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@Data
public class AccountBalanceUpdate {
	
	private static final Logger logger = LogManager.getLogger(AccountBalanceUpdate.class);

    @Autowired
    private Account_repository accountRepository1;

    public void creditToAccount(Account accountToCredit, BigDecimal txn_amt){
        accountToCredit.setAvailable_balance(
                accountToCredit.getAvailable_balance()
                        .add(txn_amt));
        accountRepository1.save(accountToCredit);
    }
    public void debitFromAccount(Account accountToCredit, BigDecimal txn_amt){
        accountToCredit.setAvailable_balance(
                accountToCredit.getAvailable_balance()
                        .subtract(txn_amt));
        accountRepository1.save(accountToCredit);
    }
    
    //business rules to validate transaction limits
    public boolean checkTransactionLimit(TransactionDTO txn_input) {
    	if(txn_input.getTxnType() == TransactionConstants.NEFT) {
    		if(txn_input.getTxnAmt().compareTo(new BigDecimal("200000")) >=0) {
    			throw new CustomErrorMessage(TransactionConstants.TRANSACTION_LIMIT_CROSS);
    		}
    	}
    	
    	
    	if(txn_input.getTxnType() == TransactionConstants.CASH_TRANSACTION) {
    		if(txn_input.getTxnAmt().compareTo(new BigDecimal("50000")) >=0) {
    			throw new CustomErrorMessage(TransactionConstants.TRANSACTION_LIMIT_CROSS);
    		}
    	}
    	logger.info("Transaction Limit Check Completed : Transaction Amount ia as per given LIMIT");
		return true;
    }

}
