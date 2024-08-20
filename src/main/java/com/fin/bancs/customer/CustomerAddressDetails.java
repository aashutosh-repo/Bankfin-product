package com.fin.bancs.customer;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CustomerAddressDetails {
	@EmbeddedId
	private AddressID addressId;
	private int addressType;
	private String addressLn1;
	private String addressLn2;
	private String city;
	private String village;
	private String district;
	private String taluka;
	private String state;
	private int pinCode;
	private LocalDate lastUpdate;
	private Date dateOfCapture;
}
