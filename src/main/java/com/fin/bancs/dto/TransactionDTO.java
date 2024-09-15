package com.fin.bancs.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TransactionDTO {
	
	private int txnType;
    private int creditDebitFlag;
    private int txnSeq;
    private int accountIdCr;
    private String accountTypeCr;
    private int accountIdDr;
    private String accountTypeDr;
    private LocalDate genDt;
    private BigDecimal txnAmt;
    private BigDecimal interestAmt;
    private BigDecimal gstAmt;
    private LocalDate lastTxnDt;
    private LocalDate lastUpdate;
    private String txnDesc;
    private String currency;

}
