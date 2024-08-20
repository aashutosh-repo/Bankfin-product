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

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class DocumentsDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int docId; // Primary key
	private int custId;
	private String docDescription;
	private String docIdentificationNumber;
	private String docType;
	private String DocTypeCode;
	private LocalDate issueDate;
	private LocalDate expiryDate;
}
