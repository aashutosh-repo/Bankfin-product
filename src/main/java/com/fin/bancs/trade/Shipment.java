package com.fin.bancs.trade;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.EmbeddedId;
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
public class Shipment {
	@EmbeddedId
	private ShipmentID shipmentId;
	private String contractId;
	private String trackingNumber;
	private String shipperName;
	private String shipperID;
	private String shipperAddress;
	private String consigneeName;
	private String consigneeID;
	private String consigneeAddress;
	private String shipmentStatus;
	private LocalDate shipmentDate;
	private LocalDate deliveryDate;
}
