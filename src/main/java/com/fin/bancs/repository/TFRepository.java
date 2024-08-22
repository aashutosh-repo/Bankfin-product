package com.fin.bancs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.trade.TradeFinance;


public interface TFRepository extends JpaRepository<TradeFinance, String> {
	  Optional<TradeFinance> findByShipmentId(String shipmentId);
}
