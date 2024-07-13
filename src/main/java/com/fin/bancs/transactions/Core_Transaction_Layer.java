package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Core_Transaction_Layer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int cen_txn_id;
	protected int txn_type; //cash or transfer
	protected int credit_debit_flag;
	protected int txn_seq;
	protected int account_id_cr;
	protected int account_type_cr;
	protected int account_id_dr;
	protected int account_type_dr;
	protected LocalDate gen_dt;
	protected BigDecimal txn_amt;
	protected BigDecimal interest_amt;
	protected BigDecimal gst_amt;
	protected int last_txn_dt;
	protected LocalDate last_update = LocalDate.now();
	protected String txn_desc;
	protected String currency;
	public Core_Transaction_Layer (){

	}
	public Core_Transaction_Layer(int cen_txn_id, int txn_type, int credit_debit_flag, int txn_seq, int account_id_cr,
			int account_type_cr, int account_id_dr, int account_type_dr, LocalDate gen_dt, BigDecimal txn_amt,
			BigDecimal interest_amt, BigDecimal gst_amt, int last_txn_dt, LocalDate last_update, String txn_desc,
			String currency) {
		super();
		this.cen_txn_id = cen_txn_id;
		this.txn_type = txn_type;
		this.credit_debit_flag = credit_debit_flag;
		this.txn_seq = txn_seq;
		this.account_id_cr = account_id_cr;
		this.account_type_cr = account_type_cr;
		this.account_id_dr = account_id_dr;
		this.account_type_dr = account_type_dr;
		this.gen_dt = gen_dt;
		this.txn_amt = txn_amt;
		this.interest_amt = interest_amt;
		this.gst_amt = gst_amt;
		this.last_txn_dt = last_txn_dt;
		this.last_update = last_update;
		this.txn_desc = txn_desc;
		this.currency = currency;
	}
	public int getCen_txn_id() {
		return cen_txn_id;
	}
	public void setCen_txn_id(int cen_txn_id) {
		this.cen_txn_id = cen_txn_id;
	}
	public int getTxn_type() {
		return txn_type;
	}
	public void setTxn_type(int txn_type) {
		this.txn_type = txn_type;
	}
	public int getCredit_debit_flag() {
		return credit_debit_flag;
	}
	public void setCredit_debit_flag(int credit_debit_flag) {
		this.credit_debit_flag = credit_debit_flag;
	}
	public int getTxn_seq() {
		return txn_seq;
	}
	public void setTxn_seq(int txn_seq) {
		this.txn_seq = txn_seq;
	}
	public int getAccount_id_cr() {
		return account_id_cr;
	}
	public void setAccount_id_cr(int account_id_cr) {
		this.account_id_cr = account_id_cr;
	}
	public int getAccount_type_cr() {
		return account_type_cr;
	}
	public void setAccount_type_cr(int account_type_cr) {
		this.account_type_cr = account_type_cr;
	}
	public int getAccount_id_dr() {
		return account_id_dr;
	}
	public void setAccount_id_dr(int account_id_dr) {
		this.account_id_dr = account_id_dr;
	}
	public int getAccount_type_dr() {
		return account_type_dr;
	}
	public void setAccount_type_dr(int account_type_dr) {
		this.account_type_dr = account_type_dr;
	}
	public LocalDate getGen_dt() {
		return gen_dt;
	}
	public void setGen_dt(LocalDate gen_dt) {
		this.gen_dt = gen_dt;
	}
	public BigDecimal getTxn_amt() {
		return txn_amt;
	}
	public void setTxn_amt(BigDecimal txn_amt) {
		this.txn_amt = txn_amt;
	}
	public BigDecimal getInterest_amt() {
		return interest_amt;
	}
	public void setInterest_amt(BigDecimal interest_amt) {
		this.interest_amt = interest_amt;
	}
	public BigDecimal getGst_amt() {
		return gst_amt;
	}
	public void setGst_amt(BigDecimal gst_amt) {
		this.gst_amt = gst_amt;
	}
	public int getLast_txn_dt() {
		return last_txn_dt;
	}
	public void setLast_txn_dt(int last_txn_dt) {
		this.last_txn_dt = last_txn_dt;
	}
	public LocalDate getLast_update() {
		return last_update;
	}
	public void setLast_update(LocalDate last_update) {
		this.last_update = LocalDate.now();
	}
	public String getTxn_desc() {
		return txn_desc;
	}
	public void setTxn_desc(String txn_desc) {
		this.txn_desc = txn_desc;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "Core_Transaction_Layer [cen_txn_id=" + cen_txn_id + ", txn_type=" + txn_type + ", credit_debit_flag="
				+ credit_debit_flag + ", txn_seq=" + txn_seq + ", account_id_cr=" + account_id_cr + ", account_type_cr="
				+ account_type_cr + ", account_id_dr=" + account_id_dr + ", account_type_dr=" + account_type_dr
				+ ", gen_dt=" + gen_dt + ", txn_amt=" + txn_amt + ", interest_amt=" + interest_amt + ", gst_amt="
				+ gst_amt + ", last_txn_dt=" + last_txn_dt + ", last_update=" + last_update + ", txn_desc=" + txn_desc
				+ ", currency=" + currency + "]";
	}
	
	

}
