package com.fin.bancs.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.bancs.repository.ShipmentRepository;
import com.fin.bancs.repository.TFRepository;
import com.fin.bancs.trade.Shipment;
import com.fin.bancs.trade.ShipmentID;
import com.fin.bancs.trade.TradeFinance;
import com.fin.bancs.utils.SequenceGenerator;


@Service
public class TradeServices {
	
	@Autowired
	TFRepository tradefinRepo;
	@Autowired
	ShipmentRepository shipmentrepo;
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	public boolean isPaymentCompleted(String ShipmentId) {
		Optional<TradeFinance> tf  = tradefinRepo.findByShipmentId(ShipmentId); 
		if(tf.isPresent()) {
			if(tf.get().getTradeStatus().toUpperCase() == "COMPLETED") {
				return true;
			}
		}
		return false;
	}
	public void createTrade(TradeFinance tradef, Shipment senderShipmentDtls, Shipment receiverShipmentDtls) {
	    BigInteger tradeId = sequenceGenerator.generateSequence("TradeId_seq");
	    BigInteger shipmentId = sequenceGenerator.generateSequence("shipmentId_seq");
	    tradef.setShipmentId(shipmentId.toString());
	    tradef.setTradeId(tradeId.toString());
	    tradefinRepo.save(tradef);
	    ShipmentID shipId= new ShipmentID();
	    shipId.setShipmentId(shipmentId.toString());
	    shipId.setShipmentType("Sender");
	    	senderShipmentDtls.setShipmentId(shipId);
	    	shipmentrepo.save(senderShipmentDtls);
	    	shipId.setShipmentType("Receiver");
	    	receiverShipmentDtls.setShipmentId(shipId);
	    	shipmentrepo.save(receiverShipmentDtls);
	    
	}

}
