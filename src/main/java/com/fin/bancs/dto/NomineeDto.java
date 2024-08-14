package com.fin.bancs.dto;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@XmlRootElement(name = "NomineeDetails")
@ApiModel(description = "Nominee details")
@Data
public class NomineeDto {
	
	@NotNull(message = "Owner ID cannot be null")
    @Schema(description = "Unique identifier for the owner", example = "1001")
    protected int ownerId;
	
    @Schema(description = "Unique identifier for the Nominee Ref Num", example = "1001")
	protected int nomineeRefNum;

    @NotNull(message = "Owner type cannot be null")
    @Schema(description = "Type of the owner", example = "1")
    protected int ownerType;

    @NotNull(message = "Sequence number cannot be null")
    @Schema(description = "Sequence number of the nominee", example = "1")
    protected int SeqNum;

    @NotNull(message = "Nominee share percentage cannot be null")
    @Schema(description = "Percentage share allocated to the nominee", example = "50")
    protected int nomShare;

    @NotNull(message = "Nominee type cannot be null")
    @Schema(description = "Type of the nominee, major or minor", example = "1")
    protected int nomType; // 1 for Major, 2 for Minor

    @Schema(description = "Code representing the nominee type", example = "1")
    protected int nomTypeCode;

    @NotEmpty(message = "Nominee first name cannot be null or empty")
    @Schema(description = "First name of the nominee", example = "John")
    protected String nomineeFirstName;

    @Schema(description = "Middle name of the nominee", example = "A")
    protected String nomineeMiddleName;

    @NotEmpty(message = "Nominee last name cannot be null or empty")
    @Schema(description = "Last name of the nominee", example = "Doe")
    protected String nomineeLastName;

    @NotNull(message = "Relation type cannot be null")
    @Schema(description = "Type of relation with the nominee", example = "1")
    protected int rtlnType; // 1 for Parent, 2 for Sibling, etc.

    @Schema(description = "Code representing the type of relation", example = "1")
    protected int rtlnTypeCode;

    @Schema(description = "Date of birth of the nominee", example = "2000-01-01")
    protected LocalDate dateOfBirth;

    @Schema(description = "Unique identifier for the nominee's address", example = "5001")
    protected int nomAddId;

    @NotEmpty(message = "Nominee document ID cannot be null or empty")
    @Schema(description = "Unique identifier for the nominee's document", example = "DOC123")
    protected String nomDocId;

    @NotNull(message = "Version number cannot be null")
    @Schema(description = "Version number of the nominee details", example = "1")
    protected int ver;

}
