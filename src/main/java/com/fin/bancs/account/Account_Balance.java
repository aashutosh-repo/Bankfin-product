package com.fin.bancs.account;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Account_Balance {
	@EmbeddedId
	protected AccountBalancePk acc_bal_id; //PK
	protected LocalDate balance_date;
	protected int txn_type;
	protected long interest_rate;
	protected BigDecimal prncpl_amt;
	protected BigDecimal intrst_rate;
	protected BigDecimal total_bal;
	protected BigDecimal opening_bal;
	protected BigDecimal available_balance;
}
