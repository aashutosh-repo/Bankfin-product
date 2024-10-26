package com.fin.bancs.instrument;

import com.fin.bancs.dto.EMIDetailDTO;
import com.fin.bancs.dto.LoanSummaryDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EMIService {

    public List<EMIDetailDTO> calculateEMIPlans(double loanAmount, double annualInterestRate, int tenureInMonths, LocalDate repaymentStartDate) {
        double monthlyInterestRate = annualInterestRate / (12 * 100);
        List<EMIDetailDTO> emiPlans = new ArrayList<>();
        double principalAmount = loanAmount / tenureInMonths;

        for (int month = 1; month <= tenureInMonths; month++) {
            double interestAmount = loanAmount * monthlyInterestRate;
            double emiAmount = principalAmount + interestAmount;

            // Create a LocalDate for the EMI date (assuming monthly payments)
            LocalDate emiDate = LocalDate.now().plusMonths(month);

            // Create EMIDetailDTO and add to the list
            EMIDetailDTO emiDetail = new EMIDetailDTO(month, emiDate, emiAmount, principalAmount, interestAmount);
            emiPlans.add(emiDetail);

            // Reduce the loan amount by the principal portion
            loanAmount -= principalAmount;
        }

        return emiPlans;
    }

    public LoanSummaryDTO calculateLoanSummary(BigDecimal loanAmount, BigDecimal annualInterestRate, int tenureInMonths) {
        // Calculate monthly interest rate
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(1200), 10, RoundingMode.HALF_UP);

        // Calculate the monthly EMI using the formula(1+R)
        BigDecimal onePlusInterest = monthlyInterestRate.add(BigDecimal.ONE);
        //X= (1+R)^N
        BigDecimal power = onePlusInterest.pow(tenureInMonths);

        //  [P*R*X]/[X-1]
        BigDecimal monthlyEMI = loanAmount.multiply(monthlyInterestRate).multiply(power)
                .divide(power.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);

        // Calculate total amount payable and total interest amount
        BigDecimal totalAmountPayable = monthlyEMI.multiply(BigDecimal.valueOf(tenureInMonths));
        BigDecimal totalInterestAmount = totalAmountPayable.subtract(loanAmount);

        return new LoanSummaryDTO(monthlyEMI, loanAmount, totalInterestAmount, totalAmountPayable);
    }

}
