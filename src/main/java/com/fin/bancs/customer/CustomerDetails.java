package com.fin.bancs.customer;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@IdClass(CustomerID.class)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Customer_Details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
