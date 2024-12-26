package com.fin.bancs.account;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fin.bancs.audit.AuditInfo;

import jakarta.persistence.Embeddable;
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
@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Account_Balance_Details extends AuditInfo{
	@EmbeddedId
	private AccountBalancePk acc_bal_id; //PK
	private String accountNumber;
	private LocalDate balance_date;
	private int txn_type;
	private BigDecimal interest_rate;
	private BigDecimal transaction_Amount;
	private BigDecimal available_balance;
	private BigDecimal blocked_Amount;
	private BigDecimal total_bal;
	private BigDecimal opening_bal;
}
