package com.fin.bancs.dto;

import com.fin.bancs.validation.annotations.ValidCard;
import com.fin.bancs.validation.annotations.ValidUpiId;
import lombok.Data;

@Data
public class PaymentDetails {
    private String paymentMethod;
    private String customerName;
    @ValidCard
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    @ValidUpiId
    private String upiId;
}
