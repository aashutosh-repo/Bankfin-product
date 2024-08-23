package com.fin.bancs.mapper;

import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.transactions.Core_Transaction;

public class TransactionMapper {
	
	public static TransactionDTO  mapToTransactionDTO(Core_Transaction entity,TransactionDTO dto) {
	    dto.setTxnType(entity.getTxn_type());
        dto.setCreditDebitFlag(entity.getCredit_debit_flag());
        dto.setTxnSeq(entity.getTxn_seq());
        dto.setAccountIdCr(entity.getAccount_id_cr());
        dto.setAccountTypeCr(entity.getAccount_type_cr());
        dto.setAccountIdDr(entity.getAccount_id_dr());
        dto.setAccountTypeDr(entity.getAccount_type_dr());
        dto.setGenDt(entity.getGen_dt());
        dto.setTxnAmt(entity.getTxn_amt());
        dto.setInterestAmt(entity.getInterest_amt());
        dto.setGstAmt(entity.getGst_amt());
        dto.setLastUpdate(entity.getLast_update());
        dto.setTxnDesc(entity.getTxn_desc());
        dto.setCurrency(entity.getCurrency());

        return dto;
	}
	public static Core_Transaction mapTOCoreTxnLayer(TransactionDTO dto,Core_Transaction entity) {
		entity.setTxn_type(dto.getTxnType());
        entity.setCredit_debit_flag(dto.getCreditDebitFlag());
        entity.setTxn_seq(dto.getTxnSeq());
        entity.setAccount_id_cr(dto.getAccountIdCr());
        entity.setAccount_type_cr(dto.getAccountTypeCr());
        entity.setAccount_id_dr(dto.getAccountIdDr());
        entity.setAccount_type_dr(dto.getAccountTypeDr());
        entity.setGen_dt(dto.getGenDt());
        entity.setTxn_amt(dto.getTxnAmt());
        entity.setInterest_amt(dto.getInterestAmt());
        entity.setGst_amt(dto.getGstAmt());
        entity.setLast_update(dto.getLastUpdate());
        entity.setTxn_desc(dto.getTxnDesc());
        entity.setCurrency(dto.getCurrency());

        return entity;
	}

}
