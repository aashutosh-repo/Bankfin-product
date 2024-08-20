package com.fin.bancs.dto;

import java.time.LocalDate;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentsDtlsDto {

    @NotNull(message = "Customer ID cannot be null")
    @Schema(description = "Unique identifier for the customer", example = "1001")
    protected int custId;

    @NotEmpty(message = "Document description cannot be null or empty")
    @Schema(description = "Description of the document", example = "Passport")
    protected String docDescription;

    @NotEmpty(message = "Document identification number cannot be null or empty")
    @Schema(description = "Unique identification number of the document", example = "A123456789")
    protected String docIdentificationNumber;

    @NotEmpty(message = "Document type cannot be null or empty")
    @Schema(description = "Type of the document", example = "ID Card")
    protected String docType;

    @NotEmpty(message = "Document type code cannot be null or empty")
    @Schema(description = "Code representing the type of document", example = "ID")
    protected String docTypeCode;

    @NotNull(message = "Issue date cannot be null")
    @Schema(description = "Date when the document was issued", example = "2023-01-01")
    protected LocalDate issueDate;

    @NotNull(message = "Expiry date cannot be null")
    @Schema(description = "Date when the document expires", example = "2033-01-01")
    protected LocalDate expiryDate;
}
