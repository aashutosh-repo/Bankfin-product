package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NegativeOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Central_Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int CEN_TXN_ID;
	protected int TXN_TYPE; //cash or transfer
	protected int credit_debit_flag;
	protected int TXN_SEQ;
	protected int ACCOUNT_ID_CR;
	protected int ACCOUNT_TYPE_CR;
	protected int ACCOUNT_ID_DR;
	protected int ACCOUNT_TYPE_DR;
	protected LocalDate GEN_DT;
	protected BigDecimal TXN_AMT;
	protected BigDecimal INTEREST_AMT;
	protected BigDecimal GST_AMT;
	protected int LAST_TXN_DT;
	protected String TXN_DESC;
}
