package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.Optional;

import com.fin.bancs.account.LineOfCredit;
import com.fin.bancs.repository.LIneOfCreditRepository;
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
	@Autowired
	private LIneOfCreditRepository lIneOfCreditRepository;
	
	public boolean isPaymentCompleted(String ShipmentId,long locId) {
		Optional<LineOfCredit> loc  = lIneOfCreditRepository.findById(locId);
        return loc.filter(lineOfCredit -> lineOfCredit.getLocStatus().equalsIgnoreCase("COMPLETED")).isPresent();
    }

	public boolean isShipmentCompleted(String ShipmentId) {
		Optional<TradeFinance> tf  = tradefinRepo.findByShipmentId(ShipmentId);
        return tf.filter(tradeFinance -> tradeFinance.getTradeStatus().equalsIgnoreCase("COMPLETED")).isPresent();
    }
	public void createTrade(TradeFinance tradefinance, Shipment senderShipmentDtls, Shipment receiverShipmentDtls) {
		//1. Contract should be created between Buyer And seller
		//2. Buyer And Seller Should be registered to bank
		//3. when contract created at the same time LOC should be created in favour of Exporter by Importer
		//4. Loc should be created in issuing Bank in contact with Advising bank from Exporter side
		//5. Shipment should be registered with Dynamic status
		//6. As Status of Shipment change to "completed" the LOC amount should be released from issuing bank to advising bank
	    BigInteger tradeId = sequenceGenerator.generateSequence("TradeId_seq");
	    BigInteger shipmentId = sequenceGenerator.generateSequence("shipmentId_seq");
	    tradefinance.setShipmentId(shipmentId.toString());
	    tradefinance.setTradeId(tradeId.toString());
	    tradefinRepo.save(tradefinance);
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
