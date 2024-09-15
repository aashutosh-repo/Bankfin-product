package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CashTransactionInput {
	
	private int txnType;
    private int creditDebitFlag;
    private int accountId;
    private String accountType;
    private BigDecimal txnAmt;
    private BigDecimal interestAmt;
    private BigDecimal gstAmt;
    private String txnDesc;
    private String currency;

}
