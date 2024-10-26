package com.fin.bancs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EMIDetailDTO {
    private int emiSequence;     // Sequence number of the EMI
    private LocalDate emiDate;   // Date of the EMI payment
    private double emiAmount;     // Total EMI amount
    private double principalAmount; // Principal component of the EMI
    private double interestAmount;
}
