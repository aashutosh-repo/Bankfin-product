package com.fin.bancs.mapper;

import com.fin.bancs.dto.ShipmentDTO;
import com.fin.bancs.dto.TradeFinanceDTO;
import com.fin.bancs.trade.Shipment;
import com.fin.bancs.trade.TradeFinance;

import lombok.Getter;

@Getter 
public class RequestWrapperShipment {
	public TradeFinanceDTO tradeFinance;
	public ShipmentDTO receiverDetails;
	public ShipmentDTO senderDetails;

}
