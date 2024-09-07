package com.fin.bancs.instrument;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.fin.bancs.dto.SipCalculatorDto;

public class SIPCalculator {

    // Method to calculate the future value of a SIP
	public SipCalculatorDto calculateSIPFutureValue(BigDecimal monthlyInvestment, BigDecimal annualInterestRate, int months) {
        // Convert annual interest rate to monthly interest rate
		SipCalculatorDto out = new SipCalculatorDto();
		out.setInvestedAmount(monthlyInvestment);
		out.setNumberOfMonths(months);
		out.setInterestRate(annualInterestRate);
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(12), 
        		MathContext.DECIMAL64).divide(BigDecimal.valueOf(100), MathContext.DECIMAL64);
        
        // Calculate the future value( Compounding math Formulae)
        
        BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyInterestRate);
        BigDecimal powerTerm = onePlusRate.pow(months, MathContext.DECIMAL64);
        BigDecimal numerator = powerTerm.subtract(BigDecimal.ONE);

        BigDecimal futureValue = monthlyInvestment.multiply(numerator.divide
        		(monthlyInterestRate, MathContext.DECIMAL64)).multiply(onePlusRate);
        
        // Use setScale with RoundingMode for proper rounding
        out.setTotalReturnValue(futureValue.setScale(2, RoundingMode.HALF_UP));  // Round to 2 decimal places
        return out;
    }
}
