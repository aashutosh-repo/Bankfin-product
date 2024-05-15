package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.AM.Account;
import com.fin.bancs.AM.AccountPk;

public interface Account_repository extends JpaRepository<Account, AccountPk>{

}
