package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.account.Account;
import com.fin.bancs.account.AccountPk;

public interface Account_repository extends JpaRepository<Account, AccountPk>{
 List<Account> findByCustId(int custId);
}
