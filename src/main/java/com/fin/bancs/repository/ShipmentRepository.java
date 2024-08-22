package com.fin.bancs.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.trade.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
	  
	  Optional<Shipment> findByTrackingNumber(String trackingNumber);
	  Optional<Shipment> findByShipmentId(String shipmentId);
	}
