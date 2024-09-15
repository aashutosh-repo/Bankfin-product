package com.fin.bancs.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.trade.Shipment;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	Optional<Shipment> findByTrackingNumber(String trackingNumber);
	Optional<Shipment> findByShipmentId(String shipmentId);
	Optional<Shipment> findByContractId(String shipmentId);
	}
