package com.fin.bancs.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fin.bancs.customer.AddressID;

import jakarta.persistence.EmbeddedId;
import lombok.Data;

@Data
public class CustomerAddressDto{
	private int customerID;
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
