package com.fin.bancs.transactions;

import lombok.Data;

@Data
public class NeftTransactionOut {
	
	private String transactionRef;
	private String status;
	private String message;
	private String transactionDate;

}
