package com.fin.bancs.BP;

import com.fin.bancs.repository.Customer_Address_Repository;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity
@IdClass(Cust_Address_detailsPk.class)
public class Customer_Address_Details {
	@Id
//	@GeneratedValue(generator = "ADDRESS_ID-seq-generator")
//	@GenericGenerator(
//			name = "ADDRESS_ID-generator",
//			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//			parameters = {
//					@Parameter(name = "ADDRESS_ID_seq", value = "user_sequence"),
//					@Parameter(name = "initial_value", value = "20000"),
//					@Parameter(name = "increment_size", value = "1")
//			})
	protected int ADDRESS_ID; //PK
	@Id
	protected int PIN_CODE; //PK
	protected int ADDRESS_TYPE;
	protected String ADDRESS_LN1;
	protected String ADDRESS_LN2;
	protected String CITY;
	protected String VILLAGE;
	protected String DISTRICT;
	protected String TALUKA;
	protected String STATE;

	protected Date DATE_OF_CAPTURE;

	public Customer_Address_Details() {
	}

	public Customer_Address_Details(int ADDRESS_ID, int ADDRESS_TYPE, String ADDRESS_LN1, String ADDRESS_LN2,
									String CITY, String VILLAGE, String DISTRICT, String TALUKA, String STATE,
									int PIN_CODE, Date DATE_OF_CAPTURE) {
		this.ADDRESS_ID = ADDRESS_ID;
		this.ADDRESS_TYPE = ADDRESS_TYPE;
		this.ADDRESS_LN1 = ADDRESS_LN1;
		this.ADDRESS_LN2 = ADDRESS_LN2;
		this.CITY = CITY;
		this.VILLAGE = VILLAGE;
		this.DISTRICT = DISTRICT;
		this.TALUKA = TALUKA;
		this.STATE = STATE;
		this.PIN_CODE = PIN_CODE;
		this.DATE_OF_CAPTURE = DATE_OF_CAPTURE;
	}

	public int getADDRESS_ID() {
		return ADDRESS_ID;
	}
	public void setADDRESS_ID(int aDDRESS_ID) {
		ADDRESS_ID = aDDRESS_ID;
	}
	public int getADDRESS_TYPE() {
		return ADDRESS_TYPE;
	}
	public void setADDRESS_TYPE(int aDDRESS_TYPE) {
		ADDRESS_TYPE = aDDRESS_TYPE;
	}
	public String getADDRESS_LN1() {
		return ADDRESS_LN1;
	}
	public void setADDRESS_LN1(String aDDRESS_LN1) {
		ADDRESS_LN1 = aDDRESS_LN1;
	}
	public String getADDRESS_LN2() {
		return ADDRESS_LN2;
	}
	public void setADDRESS_LN2(String aDDRESS_LN2) {
		ADDRESS_LN2 = aDDRESS_LN2;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getVILLAGE() {
		return VILLAGE;
	}
	public void setVILLAGE(String vILLAGE) {
		VILLAGE = vILLAGE;
	}
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	public String getTALUKA() {
		return TALUKA;
	}
	public void setTALUKA(String tALUKA) {
		TALUKA = tALUKA;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public int getPIN_CODE() {
		return PIN_CODE;
	}
	public void setPIN_CODE(int pIN_CODE) {
		PIN_CODE = pIN_CODE;
	}
	public Date getDATE_OF_CAPTURE() {
		return DATE_OF_CAPTURE;
	}
	public void setDATE_OF_CAPTURE(Date dATE_OF_CAPTURE) {
		DATE_OF_CAPTURE = dATE_OF_CAPTURE;
	}


//	@Autowired
//	private EntityManager entityManager;

	
}
