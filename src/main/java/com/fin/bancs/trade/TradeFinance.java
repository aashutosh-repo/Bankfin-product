package com.fin.bancs.trade;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
    public class TradeFinance {
	@Id
    private String contractId; // Unique identifier for the trade
    private String buyerName; // Name of the buyer
    private String sellerName; // Name of the seller
    private String commodity; // Type of commodity being traded (e.g. oil, wheat, etc.)
    private double quantity; // Quantity of the commodity being traded
    private double unitPrice; // Unit price of the commodity
    private double totalValue; // Total value of the trade
    private String paymentTerms; // Payment terms (e.g. letter of credit, cash in advance, etc.)
    private String shipmentTerms; // Shipment terms (e.g. FOB, CIF, etc.)
    private LocalDate shipmentDate; // Date of shipment
    private LocalDate paymentDueDate; // Date payment is due
    private String originCountry;
    private String destinationCountry;
    private String currency; // Currency of the trade
    private String tradeStatus;

}
