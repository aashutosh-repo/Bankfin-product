package com.fin.bancs.customer;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(Cust_Address_detailsPk.class)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class Customer_Address_Details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	protected LocalDate LAST_UPDATE;
	protected Date DATE_OF_CAPTURE;	
}
