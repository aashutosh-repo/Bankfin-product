package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fin.bancs.audit.AuditInfo;

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
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Core_Transaction extends AuditInfo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cen_txn_id;
	private int txn_type; //cash or transfer
	private String txnRefId;
	private int credit_debit_flag;
	private int txn_seq;
	private int account_id_cr;
	private String account_type_cr;
	private int account_id_dr;
	private String account_type_dr;
	private LocalDateTime transactionDate;
	private LocalDate gen_dt;
	private BigDecimal txn_amt;
	private BigDecimal interest_amt;
	private BigDecimal gst_amt;
	private int last_txn_dt;
	private LocalDate last_update = LocalDate.now();
	private String txn_desc;
	private String currency;

}
