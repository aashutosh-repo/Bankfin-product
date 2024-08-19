package com.fin.bancs.controller;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.error.CustomError;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.services.CurrencyExchange;
import com.fin.bancs.services.Currency_service;
import com.google.gson.JsonElement;

import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("instrument")
public class Instruments {
	
    @Autowired
    Currency_service currency;
    @Autowired
    CurrencyExchange currencyex;
    
    @GetMapping("currency/Get")
    public Map<String, String> getCurrency() {
    	
    	return currencyex.EchangeRate();
    }
    @PostMapping("/currency/conversion")
    public String convertCurrency(@Schema(name = "Input Currency")@RequestParam String inputCurrency, 
    		@Schema(name = "Target Currency")@RequestParam String targetCurrency,
    		@RequestParam BigDecimal amount) {
    	System.out.println(inputCurrency+" "+ amount);
    	BigDecimal finalAmt= new BigDecimal(0.0);
    	finalAmt= currency.currencyConverter(inputCurrency, amount);
    	if(finalAmt ==null) {
    		throw new CustomError(ErrorCode.CURRNCY_CANNOT_CONVERTED);
    	}else {
    		return String.valueOf(finalAmt);
    	}
    }
	@GetMapping
	public String emiCalculator(@RequestParam String loanAmt,@RequestParam String interestRate,
	@RequestParam int tenure){
     
		return "String";
	}
}

