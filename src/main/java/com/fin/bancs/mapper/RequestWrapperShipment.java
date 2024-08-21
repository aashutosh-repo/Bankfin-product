package com.fin.bancs.mapper;

import com.fin.bancs.trade.Shipment;
import com.fin.bancs.trade.TradeFinance;

import lombok.Getter;

@Getter 
public class RequestWrapperShipment {
	
	public TradeFinance tradeFinance;
	public Shipment reciverDetails;
	public Shipment senderDetails;

}
