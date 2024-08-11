package com.fin.bancs.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CustomerDto {
	
	@NotEmpty(message = "First name cannot be null or empty")
    @Schema(description = "User's first name", example = "John")
    protected String firstName;

    @NotEmpty(message = "Last name cannot be null or empty")
    @Schema(description = "User's last name", example = "Doe")
    protected String lastName;

    @NotEmpty(message = "Father's name cannot be null or empty")
    @Schema(description = "User's father's name", example = "Robert Doe")
    protected String fatherName;

    @NotEmpty(message = "Mother's name cannot be null or empty")
    @Schema(description = "User's mother's name", example = "Jane Doe")
    protected String motherName;

    @NotEmpty(message = "Email cannot be null or empty")
    @Schema(description = "User's email address", example = "john.doe@example.com")
    protected String mail;

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Schema(description = "User's mobile number", example = "+1234567890")
    protected String mobileNumber;

    @NotNull(message = "Status cannot be null")
    @Schema(description = "User's account status", example = "1")
    protected int status;

    @NotNull(message = "Date of birth cannot be null")
    @Schema(description = "User's date of birth", example = "1990-01-01")
    protected LocalDate dateOfBirth;

    @NotNull(message = "Onboarding date cannot be null")
    @Schema(description = "Date when the user was onboarded", example = "2024-01-01")
    protected LocalDate onboardingDate;

    @Schema(description = "Date when the account was closed", example = "2024-12-31")
    protected LocalDate custClsngDt;

    @NotNull(message = "Risk profile cannot be null")
    @Schema(description = "User's risk profile level", example = "2")
    protected int riskProfile;

    @NotEmpty(message = "Rating agency cannot be null or empty")
    @Schema(description = "Name of the rating agency", example = "Moody's")
    protected String ratingAgency;
	
}
