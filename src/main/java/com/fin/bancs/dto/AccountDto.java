package com.fin.bancs.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDto {
	@NotNull(message = "Account status cannot be null")
    @Schema(description = "Status of the account", example = "1")
    protected int account_status;

    @NotEmpty(message = "Account number cannot be null or empty")
    @Schema(description = "Unique identifier for the account", example = "123456789")
    protected String account_number;

    @NotNull(message = "Account open date cannot be null")
    @Schema(description = "Date when the account was opened", example = "2024-01-01")
    protected LocalDate account_open_dt;

    @NotEmpty(message = "Currency cannot be null or empty")
    @Schema(description = "Currency used for the account", example = "USD")
    protected String currency;

    @NotNull(message = "Customer ID cannot be null")
    @Schema(description = "Unique identifier for the customer", example = "1001")
    protected int cust_id;

    @NotNull(message = "Customer type cannot be null")
    @Schema(description = "Type of the customer", example = "1")
    protected int cus_type;

    @NotNull(message = "NPA status cannot be null")
    @Schema(description = "Non-Performing Asset status", example = "0")
    protected int npa_status;

    @NotNull(message = "Minimum balance cannot be null")
    @Schema(description = "Minimum balance required for the account", example = "500")
    protected int min_bal;

    @NotNull(message = "Last withdrawal date cannot be null")
    @Schema(description = "Date of the last withdrawal", example = "2024-01-15")
    protected Date last_withdrawal_dt;

    @NotNull(message = "Available balance cannot be null")
    @Schema(description = "Current available balance in the account", example = "1500.00")
    protected BigDecimal available_balance;

    @NotEmpty(message = "Owner name cannot be null or empty")
    @Schema(description = "Name of the account owner", example = "John Doe")
    protected String owner_name;

    @NotNull(message = "ATM request flag cannot be null")
    @Schema(description = "Flag indicating if an ATM request is needed", example = "1")
    protected int atm_req_flag;

    @NotNull(message = "Cheque request flag cannot be null")
    @Schema(description = "Flag indicating if a cheque book is requested", example = "0")
    protected int cheq_req_flag;

    @NotNull(message = "SMS request flag cannot be null")
    @Schema(description = "Flag indicating if SMS alerts are requested", example = "1")
    protected int sms_req_flag;

    @NotNull(message = "Closure date cannot be null")
    @Schema(description = "Date when the account was closed", example = "2024-12-31")
    protected LocalDate clsr_dt;

    @Schema(description = "Reason for account closure", example = "Customer requested closure")
    protected String clsr_reason;
}
