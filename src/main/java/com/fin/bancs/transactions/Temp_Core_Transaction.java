
package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Temp_Core_Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int txn_id;
	private int txn_type; //cash or transfer
	private int txn_Status;
	private int credit_debit_flag;
	private int txn_seq;
	private int account_id;
	private int account_type;
	private LocalDateTime transactionDate;
	private BigDecimal txn_amt;
	private BigDecimal interest_amt;
	private BigDecimal gst_amt;
	private String txn_desc;
	private String currency;

}
