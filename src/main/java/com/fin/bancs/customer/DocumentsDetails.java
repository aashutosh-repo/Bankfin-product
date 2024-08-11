package com.fin.bancs.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Documents_Details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int DOC_ID; //pk
	protected int CUST_ID;
	protected String DOC_DESCRIPTION;
	protected String DOC_IDENTIFICATION_NUM;
	protected String DOC_TYPE;
	protected String dOC_TYPE_CD;
	protected Date ISSUE_DATE;
	protected LocalDate LAST_UPDATE;
	protected Date EXPIRY_DATE;
}
