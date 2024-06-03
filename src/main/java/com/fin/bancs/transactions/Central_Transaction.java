package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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
	protected LocalDate LAST_UPDATE = LocalDate.now();
	protected String TXN_DESC;
	public Central_Transaction (){

	}
	public Central_Transaction(int cEN_TXN_ID, int tXN_TYPE, int tXN_SEQ, int aCCOUNT_ID_CR, int aCCOUNT_TYPE_CR,
			int aCCOUNT_ID_DR, int aCCOUNT_TYPE_DR, LocalDate gEN_DT, BigDecimal tXN_AMT, BigDecimal iNTEREST_AMT,
			BigDecimal gST_AMT, int lAST_TXN_DT, LocalDate lAST_UPDATE, String tXN_DESC) {
		super();
		CEN_TXN_ID = cEN_TXN_ID;
		TXN_TYPE = tXN_TYPE;
		TXN_SEQ = tXN_SEQ;
		ACCOUNT_ID_CR = aCCOUNT_ID_CR;
		ACCOUNT_TYPE_CR = aCCOUNT_TYPE_CR;
		ACCOUNT_ID_DR = aCCOUNT_ID_DR;
		ACCOUNT_TYPE_DR = aCCOUNT_TYPE_DR;
		GEN_DT = gEN_DT;
		TXN_AMT = tXN_AMT;
		INTEREST_AMT = iNTEREST_AMT;
		GST_AMT = gST_AMT;
		LAST_TXN_DT = lAST_TXN_DT;
		LAST_UPDATE = LocalDate.now();
		TXN_DESC = tXN_DESC;
	}
	public int getCEN_TXN_ID() {
		return CEN_TXN_ID;
	}
//	public void setCEN_TXN_ID(int cEN_TXN_ID) {
//		CEN_TXN_ID = cEN_TXN_ID;
//	}
	public int getTXN_TYPE() {
		return TXN_TYPE;
	}
	public void setTXN_TYPE(int tXN_TYPE) {
		TXN_TYPE = tXN_TYPE;
	}
	public int getTXN_SEQ() {
		return TXN_SEQ;
	}
	public void setTXN_SEQ(int tXN_SEQ) {
		TXN_SEQ = tXN_SEQ;
	}
	public int getACCOUNT_ID_CR() {
		return ACCOUNT_ID_CR;
	}
	public void setACCOUNT_ID_CR(int aCCOUNT_ID_CR) {
		ACCOUNT_ID_CR = aCCOUNT_ID_CR;
	}
	public int getACCOUNT_TYPE_CR() {
		return ACCOUNT_TYPE_CR;
	}
	public void setACCOUNT_TYPE_CR(int aCCOUNT_TYPE_CR) {
		ACCOUNT_TYPE_CR = aCCOUNT_TYPE_CR;
	}
	public int getACCOUNT_ID_DR() {
		return ACCOUNT_ID_DR;
	}
	public void setACCOUNT_ID_DR(int aCCOUNT_ID_DR) {
		ACCOUNT_ID_DR = aCCOUNT_ID_DR;
	}
	public int getACCOUNT_TYPE_DR() {
		return ACCOUNT_TYPE_DR;
	}
	public void setACCOUNT_TYPE_DR(int aCCOUNT_TYPE_DR) {
		ACCOUNT_TYPE_DR = aCCOUNT_TYPE_DR;
	}
	public LocalDate getGEN_DT() {
		return GEN_DT;
	}
	public void setGEN_DT(LocalDate gEN_DT) {
		GEN_DT = gEN_DT;
	}
	public BigDecimal getTXN_AMT() {
		return TXN_AMT;
	}
	public void setTXN_AMT(BigDecimal tXN_AMT) {
		TXN_AMT = tXN_AMT;
	}
	public BigDecimal getINTEREST_AMT() {
		return INTEREST_AMT;
	}
	public void setINTEREST_AMT(BigDecimal iNTEREST_AMT) {
		INTEREST_AMT = iNTEREST_AMT;
	}
	public BigDecimal getGST_AMT() {
		return GST_AMT;
	}
	public void setGST_AMT(BigDecimal gST_AMT) {
		GST_AMT = gST_AMT;
	}
	public int getLAST_TXN_DT() {
		return LAST_TXN_DT;
	}
	public void setLAST_TXN_DT(int lAST_TXN_DT) {
		LAST_TXN_DT = lAST_TXN_DT;
	}
	public LocalDate getLAST_UPDATE() {
		return LAST_UPDATE;
	}
	public void setLAST_UPDATE(LocalDate lAST_UPDATE) {
		LAST_UPDATE = LocalDate.now();
	}
	public String getTXN_DESC() {
		return TXN_DESC;
	}
	public void setTXN_DESC(String tXN_DESC) {
		TXN_DESC = tXN_DESC;
	}

	public int getCredit_debit_flag() {
		return credit_debit_flag;
	}

	public void setCredit_debit_flag(int credit_debit_flag) {
		this.credit_debit_flag = credit_debit_flag;
	}
}
