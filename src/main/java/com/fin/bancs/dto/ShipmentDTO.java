package com.fin.bancs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ShipmentDTO {
    @NotNull(message = "Shipper name cannot be null")
    @Schema(description = "Name of the shipper", example = "John Doe")
    private String shipperName;

    @NotNull(message = "Shipper ID cannot be null")
    @Schema(description = "Unique identifier for the shipper", example = "SHIPPER-123")
    private String shipperID;

    @NotNull(message = "Shipper address cannot be null")
    @Schema(description = "Address of the shipper", example = "123 Main St")
    private String shipperAddress;

    @NotNull(message = "Consignee name cannot be null")
    @Schema(description = "Name of the consignee", example = "Jane Doe")
    private String consigneeName;

    @NotNull(message = "Consignee ID cannot be null")
    @Schema(description = "Unique identifier for the consignee", example = "CONSIGNEE-123")
    private String consigneeID;

    @NotNull(message = "Consignee address cannot be null")
    @Schema(description = "Address of the consignee", example = "456 Elm St")
    private String consigneeAddress;

    @NotNull(message = "Shipment status cannot be null")
    @Schema(description = "Status of the shipment", example = "IN_TRANSIT")
    private String shipmentStatus;

    @NotNull(message = "Shipment date cannot be null")
    @Schema(description = "Date of shipment", example = "2022-01-01")
    private LocalDate shipmentDate;

    @NotNull(message = "Delivery date cannot be null")
    @Schema(description = "Expected delivery date", example = "2022-01-10")
    private LocalDate deliveryDate;

}
