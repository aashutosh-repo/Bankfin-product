package com.fin.bancs.mapper;

import com.fin.bancs.dto.TradeFinanceDTO;
import com.fin.bancs.trade.TradeFinance;

public class TradeFinanceMapper {
    public static TradeFinanceDTO tradeFinanceToTradeFinanceDTO(TradeFinance tradeFinance, TradeFinanceDTO tradeFinanceDTO ) {
        tradeFinanceDTO.setBuyerName(tradeFinance.getBuyerName());
        tradeFinanceDTO.setSellerName(tradeFinance.getSellerName());
        tradeFinanceDTO.setCommodity(tradeFinance.getCommodity());
        tradeFinanceDTO.setQuantity(tradeFinance.getQuantity());
        tradeFinanceDTO.setUnitPrice(tradeFinance.getUnitPrice());
        tradeFinanceDTO.setTotalValue(tradeFinance.getTotalValue());
        tradeFinanceDTO.setPaymentTerms(tradeFinance.getPaymentTerms());
        tradeFinanceDTO.setShipmentTerms(tradeFinance.getShipmentTerms());
        tradeFinanceDTO.setShipmentDate(tradeFinance.getShipmentDate());
        tradeFinanceDTO.setPaymentDueDate(tradeFinance.getPaymentDueDate());
        tradeFinanceDTO.setOriginCountry(tradeFinance.getOriginCountry());
        tradeFinanceDTO.setDestinationCountry(tradeFinance.getDestinationCountry());
        tradeFinanceDTO.setCurrency(tradeFinance.getCurrency());
        tradeFinanceDTO.setTradeStatus(tradeFinance.getTradeStatus());
        return tradeFinanceDTO;
    }

    public static TradeFinance tradeFinanceDTOToTradeFinance(TradeFinanceDTO tradeFinanceDTO, TradeFinance tradeFinance) {
        tradeFinance.setBuyerName(tradeFinanceDTO.getBuyerName());
        tradeFinance.setSellerName(tradeFinanceDTO.getSellerName());
        tradeFinance.setCommodity(tradeFinanceDTO.getCommodity());
        tradeFinance.setQuantity(tradeFinanceDTO.getQuantity());
        tradeFinance.setUnitPrice(tradeFinanceDTO.getUnitPrice());
        tradeFinance.setTotalValue(tradeFinanceDTO.getTotalValue());
        tradeFinance.setPaymentTerms(tradeFinanceDTO.getPaymentTerms());
        tradeFinance.setShipmentTerms(tradeFinanceDTO.getShipmentTerms());
        tradeFinance.setShipmentDate(tradeFinanceDTO.getShipmentDate());
        tradeFinance.setPaymentDueDate(tradeFinanceDTO.getPaymentDueDate());
        tradeFinance.setOriginCountry(tradeFinanceDTO.getOriginCountry());
        tradeFinance.setDestinationCountry(tradeFinanceDTO.getDestinationCountry());
        tradeFinance.setCurrency(tradeFinanceDTO.getCurrency());
        tradeFinance.setTradeStatus(tradeFinanceDTO.getTradeStatus());
        return tradeFinance;
    }
}