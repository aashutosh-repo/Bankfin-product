package com.fin.bancs.BP;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;


@Entity
@IdClass(CustomerID.class)
public class Customer_Details {

	@Id
	@GeneratedValue(generator = "sequence-generator")
	@GenericGenerator(
			name = "sequence-generator",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@Parameter(name = "sequence_name", value = "user_sequence"),
					@Parameter(name = "initial_value", value = "999999"),
					@Parameter(name = "increment_size", value = "1")
			}
	)
//	@GeneratedValue(generator = "CUS_ID-generator")
//	@GenericGenerator(
//			name = "CUS_ID-Seq",
//			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//			parameters = {
//					@Parameter(name = "sequence_name", value = "my_sequence"),
//					@Parameter(name = "initial_value", value = "99999999"),
//					@Parameter(name = "increment_size", value = "1")
//			})
	protected int CUS_ID; //pk
	@Id
	protected int CUS_TYP; //PK
	protected String FIRST_NAME;
	protected String LAST_NAME;
	protected String FATHER_NAME;
	protected String MOTHER_NAME;
	protected String EMAIL;
	protected String MOBILE_NUMBER;
	protected int STATUS;
	protected int ADDRESS_ID;
	protected int NOMINEE_ID;
	protected LocalDate DATE_OF_BIRTH;
	protected LocalDate CUST_CREATION_DT;
	protected LocalDate CUST_CLSNG_DT;
	protected int RISK_RATING;
	protected LocalDate LAST_UPDATE;
	protected String RATING_AGENCY;

	public Customer_Details() {
	}

	public Customer_Details(int CUS_ID, int CUS_TYP, String FIRST_NAME, String LAST_NAME, String FATHER_NAME,
							String MOTHER_NAME, String EMAIL, String MOBILE_NUMBER, int STATUS, int ADDRESS_ID,
							int NOMINEE_ID, LocalDate DATE_OF_BIRTH, LocalDate CUST_CREATION_DT, LocalDate CUST_CLSNG_DT,
							int RISK_RATING, String RATING_AGENCY,LocalDate LAST_UPDATE) {
		this.CUS_ID = CUS_ID;
		this.CUS_TYP = CUS_TYP;
		this.FIRST_NAME = FIRST_NAME;
		this.LAST_NAME = LAST_NAME;
		this.FATHER_NAME = FATHER_NAME;
		this.MOTHER_NAME = MOTHER_NAME;
		this.EMAIL = EMAIL;
		this.MOBILE_NUMBER = MOBILE_NUMBER;
		this.STATUS = STATUS;
		this.ADDRESS_ID = ADDRESS_ID;
		this.NOMINEE_ID = NOMINEE_ID;
		this.DATE_OF_BIRTH = DATE_OF_BIRTH;
		this.CUST_CREATION_DT = CUST_CREATION_DT;
		this.CUST_CLSNG_DT = CUST_CLSNG_DT;
		this.RISK_RATING = RISK_RATING;
		this.RATING_AGENCY = RATING_AGENCY;
		this.LAST_UPDATE = LocalDate.now();
	}

	public int getCUS_ID() {
		return CUS_ID;
	}
	public void setCUS_ID(int cUS_ID) {
		CUS_ID = cUS_ID;
	}
	public int getCUS_TYP() {
		return CUS_TYP;
	}
	public void setCUS_TYP(int cUS_TYP) {
		CUS_TYP = cUS_TYP;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}
	public String getFATHER_NAME() {
		return FATHER_NAME;
	}
	public void setFATHER_NAME(String fATHER_NAME) {
		FATHER_NAME = fATHER_NAME;
	}
	public String getMOTHER_NAME() {
		return MOTHER_NAME;
	}
	public void setMOTHER_NAME(String mOTHER_NAME) {
		MOTHER_NAME = mOTHER_NAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getMOBILE_NUMBER() {
		return MOBILE_NUMBER;
	}
	public void setMOBILE_NUMBER(String mOBILE_NUMBER) {
		MOBILE_NUMBER = mOBILE_NUMBER;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}
	public int getADDRESS_ID() {
		return ADDRESS_ID;
	}
	public void setADDRESS_ID(int aDDRESS_ID) {
		ADDRESS_ID = aDDRESS_ID;
	}
	public int getNOMINEE_ID() {
		return NOMINEE_ID;
	}
	public void setNOMINEE_ID(int nOMINEE_ID) {
		NOMINEE_ID = nOMINEE_ID;
	}
	public LocalDate getDATE_OF_BIRTH() {
		return DATE_OF_BIRTH;
	}
	public void setDATE_OF_BIRTH(LocalDate dATE_OF_BIRTH) {
		DATE_OF_BIRTH = dATE_OF_BIRTH;
	}
	public LocalDate getCUST_CREATION_DT() {
		return CUST_CREATION_DT;
	}
	public void setCUST_CREATION_DT(LocalDate cUST_CREATION_DT) {
		CUST_CREATION_DT = cUST_CREATION_DT;
	}
	public LocalDate getCUST_CLSNG_DT() {
		return CUST_CLSNG_DT;
	}
	public void setCUST_CLSNG_DT(LocalDate cUST_CLSNG_DT) {
		CUST_CLSNG_DT = cUST_CLSNG_DT;
	}
	public int getRISK_RATING() {
		return RISK_RATING;
	}
	public void setRISK_RATING(int rISK_RATING) {
		RISK_RATING = rISK_RATING;
	}
	public String getRATING_AGENCY() {
		return RATING_AGENCY;
	}
	public void setRATING_AGENCY(String rATING_AGENCY) {
		RATING_AGENCY = rATING_AGENCY;
	}
	public LocalDate getLAST_UPDATE() {
		return LAST_UPDATE;
	}

	public void setLAST_UPDATE(LocalDate lAST_UPDATE) {
		LAST_UPDATE = LocalDate.now();
	}

	@Override
	public String toString() {
		return "Customer_Details [CUS_ID=" + CUS_ID + ", CUS_TYP=" + CUS_TYP + ", FIRST_NAME=" + FIRST_NAME
				+ ", LAST_NAME=" + LAST_NAME + ", FATHER_NAME=" + FATHER_NAME + ", MOTHER_NAME=" + MOTHER_NAME
				+ ", EMAIL=" + EMAIL + ", MOBILE_NUMBER=" + MOBILE_NUMBER + ", STATUS=" + STATUS + ", ADDRESS_ID="
				+ ADDRESS_ID + ", NOMINEE_ID=" + NOMINEE_ID + ", DATE_OF_BIRTH=" + DATE_OF_BIRTH + ", CUST_CREATION_DT="
				+ CUST_CREATION_DT + ", CUST_CLSNG_DT=" + CUST_CLSNG_DT + ", RISK_RATING=" + RISK_RATING
				+ ", RATING_AGENCY=" + RATING_AGENCY + "]";
	}
	
}
