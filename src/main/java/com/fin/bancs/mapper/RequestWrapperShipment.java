package com.fin.bancs.mapper;

import com.fin.bancs.dto.ShipmentDTO;
import com.fin.bancs.dto.TradeFinanceDTO;

import lombok.Getter;

@Getter 
public class RequestWrapperShipment {
	private TradeFinanceDTO tradeFinance;
	private ShipmentDTO receiverDetails;
	private ShipmentDTO senderDetails;

}
