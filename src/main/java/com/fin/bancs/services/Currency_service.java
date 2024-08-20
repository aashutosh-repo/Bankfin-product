package com.fin.bancs.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.fin.bancs.common.Currency;
import com.fin.bancs.repository.Currency_Repository;
import com.fin.bancs.services.si.Currency_Service_Interface;

@Service
public class Currency_service implements Currency_Service_Interface{
    private Currency_Repository currencyRepository;  // Assuming you have a repository for the Currency table

	
	public BigDecimal currencyConverter(String refCurrency,String targetCurrencyirrncy, BigDecimal amount) {
        Optional<Currency> currencyOptional = currencyRepository.findById(refCurrency);
        
        if (currencyOptional.isPresent()) {
            Currency currency = currencyOptional.get();
            BigDecimal exchangeRate = currency.getExch_value();
            BigDecimal convertedAmount = amount.multiply(exchangeRate);
            return convertedAmount;
        } else {
            return null;
        }
		
	}

}
