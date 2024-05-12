package com.fin.bancs.BP;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Date;

@Entity
public class Documents_Details {
	@Id
//	@GeneratedValue(generator = "DOC_ID-seq-generator")
//	@GenericGenerator(
//			name = "DOC_ID-generator",
//			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//			parameters = {
//					@Parameter(name = "DOC_ID_seq", value = "user_sequence"),
//					@Parameter(name = "initial_value", value = "100000"),
//					@Parameter(name = "increment_size", value = "1")
//			})
	protected int DOC_ID; //pk
	protected int CUST_ID;
	protected String DOC_IDENTIFICATION_NUM;
	protected String DOC_TYPE;
	protected String dOC_TYPE_CD;
	protected Date ISSUE_DATE;
	protected Date EXPIRY_DATE;

	public Documents_Details() {
	}

	public Documents_Details(int DOC_ID, int CUST_ID, String DOC_IDENTIFICATION_NUM,
							 String DOC_TYPE, String dOC_TYPE_CD, Date ISSUE_DATE, Date EXPIRY_DATE) {
		this.DOC_ID = DOC_ID;
		this.CUST_ID = CUST_ID;
		this.DOC_IDENTIFICATION_NUM = DOC_IDENTIFICATION_NUM;
		this.DOC_TYPE = DOC_TYPE;
		this.dOC_TYPE_CD = dOC_TYPE_CD;
		this.ISSUE_DATE = ISSUE_DATE;
		this.EXPIRY_DATE = EXPIRY_DATE;
	}

	public int getDOC_ID() {
		return DOC_ID;
	}
	public void setDOC_ID(int dOC_ID) {
		DOC_ID = dOC_ID;
	}
	public int getCUST_ID() {
		return CUST_ID;
	}
	public void setCUST_ID(int cUST_ID) {
		CUST_ID = cUST_ID;
	}
	public String getDOC_IDENTIFICATION_NUM() {
		return DOC_IDENTIFICATION_NUM;
	}
	public void setDOC_IDENTIFICATION_NUM(String dOC_IDENTIFICATION_NUM) {
		DOC_IDENTIFICATION_NUM = dOC_IDENTIFICATION_NUM;
	}
	public String getDOC_TYPE() {
		return DOC_TYPE;
	}
	public void setDOC_TYPE(String dOC_TYPE) {
		DOC_TYPE = dOC_TYPE;
	}
	public String getdOC_TYPE_CD() {
		return dOC_TYPE_CD;
	}
	public void setdOC_TYPE_CD(String dOC_TYPE_CD) {
		this.dOC_TYPE_CD = dOC_TYPE_CD;
	}
	public Date getISSUE_DATE() {
		return ISSUE_DATE;
	}
	public void setISSUE_DATE(Date iSSUE_DATE) {
		ISSUE_DATE = iSSUE_DATE;
	}
	public Date getEXPIRY_DATE() {
		return EXPIRY_DATE;
	}
	public void setEXPIRY_DATE(Date eXPIRY_DATE) {
		EXPIRY_DATE = eXPIRY_DATE;
	}
}
