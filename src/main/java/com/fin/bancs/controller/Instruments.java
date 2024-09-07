package com.fin.bancs.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.common.AccountsConstants;
import com.fin.bancs.dto.ResponseDto;
import com.fin.bancs.dto.SipCalculatorDto;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ErrorHandler;
import com.fin.bancs.instrument.SIPCalculator;
import com.fin.bancs.services.Currency_service;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("instrument")
public class Instruments {

	@Autowired
	Currency_service currency;

	@GetMapping("currency/Get")
	public Map<String, String> getCurrency() {
	return null;
	}
	@PostMapping("/currency/conversion")
	public String convertCurrency(@Schema(name = "inputCurrency")@RequestParam String inputCurrency,
								  @Schema(name = "targetCurrency")@RequestParam String targetCurrency,
								  @RequestParam BigDecimal amount) {
		System.out.println(inputCurrency+" "+ amount);
		BigDecimal finalAmt;
		finalAmt= currency.currencyConverter(inputCurrency, targetCurrency, amount);
		if(finalAmt ==null) {
			throw new ErrorHandler(ErrorCode.CURRNCY_CANNOT_CONVERTED);
		}else {
			return String.valueOf(finalAmt);
		}
	}
	@GetMapping
	public String emiCalculator(@RequestParam String loanAmt,@RequestParam String interestRate,
								@RequestParam int tenure){

		return "String";
	}
	
	@GetMapping("/sipCalculator")
	public ResponseEntity<ResponseDto> sipCalculator(@Parameter(description = "Sip Amount", name = "SipAmount")
    							@RequestParam(name = "SipAmount") 
								@NotNull @DecimalMin(value = "0.01", message = "Sip Amount must be greater than 0")	
								BigDecimal SipAmount
								,@Parameter(description = "Expected Return Rate in percentage", name = "expectedReturnRate")
					            @RequestParam(name = "expectedReturnRate") BigDecimal expectedReturnRate,
					            @Parameter(description = "Tenure in months")
								@Min(value = 1, message = "Tenure must be at least 1 month")
    							@RequestParam(name = "tenure") int tenure){
		SIPCalculator sip = new SIPCalculator();
		
		SipCalculatorDto out= sip.calculateSIPFutureValue(SipAmount, expectedReturnRate, tenure);
		return ResponseEntity.status(HttpStatus.FOUND).body(
				new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200,out));
	}
}

