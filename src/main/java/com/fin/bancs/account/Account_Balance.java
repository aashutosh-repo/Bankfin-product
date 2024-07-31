package com.fin.bancs.account;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Account_Balance {
	@Id 
	protected int account_id; //PK
	@Id
	protected int account_type; //PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int balance_seq_id; //PK
	protected LocalDate balance_date;
	protected int txn_type;
	protected long interest_rate;
	protected BigDecimal prncpl_amt;
	protected BigDecimal intrst_rate;
	protected BigDecimal total_bal;
	protected BigDecimal opening_bal;
	protected BigDecimal available_balance;
}
