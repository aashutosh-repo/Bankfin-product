package com.fin.bancs.repository;

import org.checkerframework.common.util.report.qual.ReportCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.account.Account;
import com.fin.bancs.account.AccountPk;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Account_repository extends JpaRepository<Account, AccountPk>{
 List<Account> findByCustId(int custId);
 Account findByAccountNumber(String AccNumber);
}
