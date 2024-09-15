package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

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
	protected int cen_txn_id;
	protected int txn_type; //cash or transfer
	protected String txnRefId;
	protected int credit_debit_flag;
	protected int txn_seq;
	protected int account_id_cr;
	protected String account_type_cr;
	protected int account_id_dr;
	protected String account_type_dr;
	protected LocalDate gen_dt;
	protected BigDecimal txn_amt;
	protected BigDecimal interest_amt;
	protected BigDecimal gst_amt;
	protected int last_txn_dt;
	protected LocalDate last_update = LocalDate.now();
	protected String txn_desc;
	protected String currency;

}
