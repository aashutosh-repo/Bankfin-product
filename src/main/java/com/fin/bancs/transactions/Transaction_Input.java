package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public class Transaction_Input {
	
	protected int CEN_TXN_ID;
	protected int TXN_TYPE; //DEBIT or CREDIT
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
	protected LocalDate LAST_UPDATE = LocalDate.now();
	protected String TXN_DESC;
}
