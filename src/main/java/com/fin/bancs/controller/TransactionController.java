package com.fin.bancs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.bancs.account.AccountPk;
import com.fin.bancs.dto.ResponseDto;
import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.error.ErrorHandler;
import com.fin.bancs.services.Core_Transaction_services;
import com.fin.bancs.transactions.CashTransactionInput;
import com.fin.bancs.transactions.Core_Transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name= "Omega Bank transaction Controller",
description= "This collection contains CRUD operation for transactions")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private  Core_Transaction_services txnServices;

    //Test application
    @Operation(summary ="This Endpoint handle creation of transaction ",
    description= "To create Cash and transfer type of Transaction")
    @ApiResponse(
        responseCode= "201",
        description = "http status CREATED"
    )
    @PostMapping("/cashTransaction")
    public ResponseEntity<Object> createTxn(@RequestBody CashTransactionInput cashTxnDtls){
    	String txnId = txnServices.cashTransaction(cashTxnDtls);
    	AccountPk accId = new AccountPk();
    	accId.setAccount_id(cashTxnDtls.getAccountId());
    	accId.setAccount_type(cashTxnDtls.getAccountType());
    	txnServices.getCashtxnDetails(accId, txnId);
    	ResponseEntity<Object> respObj = ResponseDto.responseBuilder("Transaction SuccessFull", HttpStatus.OK, txnServices.getCashtxnDetails(accId, txnId));
        return respObj;
    }

    @Operation(summary= "This end point is to create transfer type transaction",
    description= "to create transfer type txn")
    @PostMapping("/transfer")
    public String transferTxn (@RequestBody List<Core_Transaction> txnInput){
        //txnServices.CreateTransaction(txnInput);
        return "Transaction is successful";
    }
    @Operation(
        summary = "This Endpoint enquiry the transactions ",
        description= "This endpoint handle enquiry for existing transaction stored in DB"
    )
    //Transaction  with TXN-ID 
    @GetMapping("/getByTxnId")
    public List<TransactionDTO> getTxn(@RequestParam String txnId){
    	if(txnId.isBlank()) {
    		throw new ErrorHandler("Txn Id should be String");
    	}
    	return txnServices.getTransactionDetails(txnId);
    }

}
