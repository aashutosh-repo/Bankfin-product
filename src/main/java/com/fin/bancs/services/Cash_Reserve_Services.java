package com.fin.bancs.services;

import java.time.LocalDateTime;

import com.fin.bancs.constants.TransactionConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.fin.bancs.error.CustomErrorMessage;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.repository.Cash_Reserve_Repository;
import com.fin.bancs.transactions.Cash_Reserve;

@Service
@AllArgsConstructor
public class Cash_Reserve_Services {
	
	private final Cash_Reserve_Repository cash_Reserve_Repository;
	
	
	public void createCashReserve(Cash_Reserve cashReserve) {
		Cash_Reserve cash_Reserve = new Cash_Reserve();
		
		cash_Reserve.setBlockedAmount(cashReserve.getBlockedAmount());
		cash_Reserve.setBlockReason(cashReserve.getBlockReason());
		cash_Reserve.setBlockStatus(TransactionConstants.cashReserveStatus.BLOCKED);
		cash_Reserve.setStartDate(LocalDateTime.now());
		cash_Reserve.setLinkedAccountId(cashReserve.getLinkedAccountId());
		cash_Reserve.setTransactionId(cashReserve.getTransactionId());
		cash_Reserve.setExpiryDate(LocalDateTime.now().plusDays(5));
		cash_Reserve_Repository.save(cash_Reserve);
	}
	
	public void expireCashReserve(String txnId) {
		Cash_Reserve cashReserve = cash_Reserve_Repository.findByTransactionId(txnId);
		if(cashReserve.getLinkedAccountId() == null) {
			throw new CustomErrorMessage(ErrorCode.CASH_BLOCK_NOT_FOUND);
		}
		cashReserve.setBlockStatus(TransactionConstants.cashReserveStatus.EXPIRED);  //Expired
		cashReserve.setExpiryDate(LocalDateTime.now());
		cash_Reserve_Repository.save(cashReserve);
	}

}
