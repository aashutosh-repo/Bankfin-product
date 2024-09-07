package com.fin.bancs.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SipCalculatorDto {
	private BigDecimal investedAmount;
	private BigDecimal interestRate;
	private int numberOfMonths;
	private BigDecimal totalReturnValue;
}
