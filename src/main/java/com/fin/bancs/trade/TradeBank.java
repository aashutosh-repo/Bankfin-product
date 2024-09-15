package com.fin.bancs.trade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor	 @NoArgsConstructor
public class TradeBank {

    @Id
    private String bankId; // Unique identifier for the bank
    private String bankName; // Name of the bank
    private String bankAddress; // Address of the bank
    private String bankCity; // City where the bank is located
    private String bankState; // State where the bank is located
    private String bankCountry; // Country where the bank is located
    private String bankZipCode; // Zip code of the bank
    private String bankSwiftCode; // SWIFT code of the bank
    private String bankRoutingNumber; // Routing number of the bank
    private String bankAccountNumber; // Account number of the bank
    private double bankBalance; // Current balance of the bank
    private String bankCurrency; // Currency used by the bank
    private String bankType; // Type of bank (e.g. commercial, investment, etc.)
    private String bankStatus; // Status of the bank (e.g. active, inactive, etc.)
}
