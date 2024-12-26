package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fin.bancs.account.AccountBalancePk;
import com.fin.bancs.account.Account_Balance_Details;

@Repository
public interface Account_Balance_Details_Repository extends JpaRepository<Account_Balance_Details, AccountBalancePk> {
	 Account_Balance_Details findByAccountNumber(String accountNumber);
}
