package com.fin.bancs.customer;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class NomineeDetails {
	@Id
	protected int nomineeId; //pk
	private int nomineeRefNum; //for external Use
	private int ownerId;
    private int ownerType;
    private int seqNum;
    private int nomShare;
    private int nomType;
    private int nomTypeCode;
    private String nomineeFirstName;
    private String nomineeMiddleName;
    private String nomineeLastName;
    private int rtlnType;
    private int rtlnTypeCode;
    private LocalDate dateOfBirth;
    private int nomAddId;
    private String nomDocId;
    private int ver;
}
