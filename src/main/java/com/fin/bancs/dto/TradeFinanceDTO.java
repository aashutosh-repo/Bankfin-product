package com.fin.bancs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TradeFinanceDTO {

    @NotNull(message = "Buyer name cannot be null")
    @Schema(description = "Name of the buyer", example = "John Doe")
    private String buyerName;

    @NotNull(message = "Seller name cannot be null")
    @Schema(description = "Name of the seller", example = "Jane Doe")
    private String sellerName;

    @NotNull(message = "Commodity cannot be null")
    @Schema(description = "Type of commodity being traded", example = "Oil")
    private String commodity;

    @NotNull(message = "Quantity cannot be null")
    @Schema(description = "Quantity of the commodity being traded", example = "1000")
    private double quantity;

    @NotNull(message = "Unit price cannot be null")
    @Schema(description = "Unit price of the commodity", example = "10.99")
    private double unitPrice;

    @NotNull(message = "Total value cannot be null")
    @Schema(description = "Total value of the trade", example = "10990.00")
    private double totalValue;

    @NotNull(message = "Payment terms cannot be null")
    @Schema(description = "Payment terms", example = "Letter of Credit")
    private String paymentTerms;

    @NotNull(message = "Shipment terms cannot be null")
    @Schema(description = "Shipment terms", example = "FOB")
    private String shipmentTerms;

    @NotNull(message = "Shipment date cannot be null")
    @Schema(description = "Date of shipment", example = "2022-01-01")
    private LocalDate shipmentDate;

    @NotNull(message = "Payment due date cannot be null")
    @Schema(description = "Date payment is due", example = "2022-01-15")
    private LocalDate paymentDueDate;

    @NotNull(message = "Origin country cannot be null")
    @Schema(description = "Country of origin", example = "USA")
    private String originCountry;

    @NotNull(message = "Destination country cannot be null")
    @Schema(description = "Destination country", example = "Canada")
    private String destinationCountry;

    @NotNull(message = "Currency cannot be null")
    @Schema(description = "Currency of the trade", example = "USD")
    private String currency;

    @NotNull(message = "Trade status cannot be null")
    @Schema(description = "Status of the trade", example = "Active")
    private String tradeStatus;
}