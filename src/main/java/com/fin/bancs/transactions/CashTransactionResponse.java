package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter@AllArgsConstructor @NoArgsConstructor
public class CashTransactionResponse {
	private String accNumber;
	private BigDecimal txnAmt;
	private BigDecimal availableAmt;
	private LocalDate txnDate;
	private String txnDesc;

}
