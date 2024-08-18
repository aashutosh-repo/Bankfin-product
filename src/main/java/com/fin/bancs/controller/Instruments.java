package com.fin.bancs.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.bancs.error.CustomError;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.services.Currency_service;



@Controller
@RequestMapping("instrument")
public class Instruments {
	
    @Autowired
    Currency_service currency;
    
    @GetMapping("currency/Get")
    public String getCurrency() {
    	
    	return "instruments/currencyconversion";
    }
    @PostMapping("/currency/conversion")
    public String convertCurrency(@RequestParam String inputCurrency, @RequestParam BigDecimal amount) {
    	System.out.println(inputCurrency+" "+amount);
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
	@RequestParam int tenure

){
     
return "String";
}


}

