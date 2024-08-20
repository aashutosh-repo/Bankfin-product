package com.fin.bancs.customer;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CustomerDetails {
	@EmbeddedId
	CustomerID customerId; //PK
	private String firstName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private String email;
	private String mobileNumber;
	private int status;
	private int addressId;
	private int nomineeId;
	private LocalDate dateOfBirth;
	private LocalDate custCreationDt;
	private LocalDate custClsngDt;
	private int riskProfile;
	private LocalDate lastUpdate;
	private String ratingAgency;
}
