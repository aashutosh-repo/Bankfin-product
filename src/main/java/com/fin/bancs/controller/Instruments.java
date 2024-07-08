package com.fin.bancs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.common.Currency;
import com.fin.bancs.repository.Currency_Repository;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class Instruments {

    @Autowired
    private Currency_Repository currencyRepository;  // Assuming you have a repository for the Currency table

    @GetMapping("/convert")
    public String convertCurrency(@RequestParam String inputCurrency, @RequestParam BigDecimal amount) {
        Optional<Currency> currencyOptional = currencyRepository.findById(inputCurrency);
        
        if (currencyOptional.isPresent()) {
            Currency currency = currencyOptional.get();
            BigDecimal exchangeRate = currency.getExch_value();
            BigDecimal convertedAmount = amount.multiply(exchangeRate);
            return String.format("%.2f %s", convertedAmount, currency.getCurrency_code());
        } else {
            return "Currency code not found.";
        }
    }
}

