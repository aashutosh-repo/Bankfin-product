package com.fin.bancs.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
public class LoanSummaryDTO {
    private BigDecimal monthlyEMI;
    private BigDecimal principalAmount;
    private BigDecimal totalInterestAmount;
    private BigDecimal totalAmountPayable;

    public LoanSummaryDTO(BigDecimal monthlyEMI, BigDecimal principalAmount, BigDecimal totalInterestAmount, BigDecimal totalAmountPayable) {
        this.monthlyEMI = monthlyEMI.setScale(2, RoundingMode.HALF_UP);
        this.principalAmount = principalAmount.setScale(2, RoundingMode.HALF_UP);
        this.totalInterestAmount = totalInterestAmount.setScale(2, RoundingMode.HALF_UP);
        this.totalAmountPayable = totalAmountPayable.setScale(2, RoundingMode.HALF_UP);
    }

}
