package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.bancs.repository.ShipmentRepository;
import com.fin.bancs.trade.Shipment;
import com.fin.bancs.trade.ShipmentID;
import com.fin.bancs.utils.SequenceGenerator;

@Service
public class ShipmentService {
  
  @Autowired
  private ShipmentRepository shipmentRepository;
  @Autowired
  private TradeServices tfservices;
	@Autowired
	private SequenceGenerator sequenceGenerator;
  
  public Shipment createShipment(Shipment shipment) {
	  BigInteger shipmentId = sequenceGenerator.generateSequence("Shipment_seq");
	  BigInteger trackingId = sequenceGenerator.generateSequence("Tracking_seq");
	  ShipmentID shipId = new ShipmentID();
	  shipId.setShipmentId("sh"+shipmentId);
	  shipId.setShipmentType("NA");
	  shipment.setTrackingNumber("IND"+trackingId);
    return shipmentRepository.save(shipment);
  }
  
  public Optional<Shipment> getShipmentByTrackingNumber(String trackingNumber) {
    return shipmentRepository.findByTrackingNumber(trackingNumber);
  }
  
  public Shipment updateShipment(Shipment shipment) {
    return shipmentRepository.save(shipment);
  }
  
  public void deleteShipment(Long id) {
    shipmentRepository.deleteById(id);
  }

public boolean isDelivered(String shipmentId) {
	Optional<Shipment> shipment= shipmentRepository.findByShipmentId(shipmentId);
	if(shipment.isEmpty()) {
		return false;
	}else if(shipment.get().getShipmentStatus().toUpperCase() == "DELIVERED"){
	 	return true;
	}
	return false;
}

public boolean isPaymentCleared(String shipmentId) {
	boolean yesNo = tfservices.isPaymentCompleted(shipmentId);
	if(yesNo == true) {
		return true;
	}
	return false;
}
}	