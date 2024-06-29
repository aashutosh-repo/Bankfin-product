package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

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

	public int getCEN_TXN_ID() {
		return CEN_TXN_ID;
	}

	public void setCEN_TXN_ID(int CEN_TXN_ID) {
		this.CEN_TXN_ID = CEN_TXN_ID;
	}

	public int getTXN_TYPE() {
		return TXN_TYPE;
	}

	public void setTXN_TYPE(int TXN_TYPE) {
		this.TXN_TYPE = TXN_TYPE;
	}

	public int getTXN_SEQ() {
		return TXN_SEQ;
	}

	public void setTXN_SEQ(int TXN_SEQ) {
		this.TXN_SEQ = TXN_SEQ;
	}

	public int getACCOUNT_ID_CR() {
		return ACCOUNT_ID_CR;
	}

	public void setACCOUNT_ID_CR(int ACCOUNT_ID_CR) {
		this.ACCOUNT_ID_CR = ACCOUNT_ID_CR;
	}

	public int getACCOUNT_TYPE_CR() {
		return ACCOUNT_TYPE_CR;
	}

	public void setACCOUNT_TYPE_CR(int ACCOUNT_TYPE_CR) {
		this.ACCOUNT_TYPE_CR = ACCOUNT_TYPE_CR;
	}

	public int getACCOUNT_ID_DR() {
		return ACCOUNT_ID_DR;
	}

	public void setACCOUNT_ID_DR(int ACCOUNT_ID_DR) {
		this.ACCOUNT_ID_DR = ACCOUNT_ID_DR;
	}

	public int getACCOUNT_TYPE_DR() {
		return ACCOUNT_TYPE_DR;
	}

	public void setACCOUNT_TYPE_DR(int ACCOUNT_TYPE_DR) {
		this.ACCOUNT_TYPE_DR = ACCOUNT_TYPE_DR;
	}

	public LocalDate getGEN_DT() {
		return GEN_DT;
	}

	public void setGEN_DT(LocalDate GEN_DT) {
		this.GEN_DT = GEN_DT;
	}

	public BigDecimal getTXN_AMT() {
		return TXN_AMT;
	}

	public void setTXN_AMT(BigDecimal TXN_AMT) {
		this.TXN_AMT = TXN_AMT;
	}

	public BigDecimal getINTEREST_AMT() {
		return INTEREST_AMT;
	}

	public void setINTEREST_AMT(BigDecimal INTEREST_AMT) {
		this.INTEREST_AMT = INTEREST_AMT;
	}

	public BigDecimal getGST_AMT() {
		return GST_AMT;
	}

	public void setGST_AMT(BigDecimal GST_AMT) {
		this.GST_AMT = GST_AMT;
	}

	public int getLAST_TXN_DT() {
		return LAST_TXN_DT;
	}

	public void setLAST_TXN_DT(int LAST_TXN_DT) {
		this.LAST_TXN_DT = LAST_TXN_DT;
	}

	public LocalDate getLAST_UPDATE() {
		return LAST_UPDATE;
	}

	public void setLAST_UPDATE(LocalDate LAST_UPDATE) {
		this.LAST_UPDATE = LAST_UPDATE;
	}

	public String getTXN_DESC() {
		return TXN_DESC;
	}

	public void setTXN_DESC(String TXN_DESC) {
		this.TXN_DESC = TXN_DESC;
	}
}
