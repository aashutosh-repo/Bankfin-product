package com.fin.bancs.services;

import com.fin.bancs.account.Account;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.transactions.CashTransactionInput;
import com.fin.bancs.transactions.Core_Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountBalanceUpdate {

    @Autowired
    private Account_repository accountRepository;

    public void creditToAccount(Account accountToCredit, BigDecimal txn_amt){
        accountToCredit.setAvailable_balance(
                accountToCredit.getAvailable_balance()
                        .add(txn_amt));
        accountRepository.save(accountToCredit);
    }
    public void debitFromAccount(Account accountToCredit, BigDecimal txn_amt){
        accountToCredit.setAvailable_balance(
                accountToCredit.getAvailable_balance()
                        .subtract(txn_amt));
        accountRepository.save(accountToCredit);
    }

}
