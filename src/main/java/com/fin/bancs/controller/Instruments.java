package com.fin.bancs.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.error.CustomException;
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
    		throw new CustomException("Currency Not Converted",101, HttpStatus
    				.NOT_IMPLEMENTED);
    	}else {
    		return String.valueOf(finalAmt);
    	}
    }
}

