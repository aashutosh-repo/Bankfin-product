package com.fin.bancs.controller;

import com.fin.bancs.repository.Core_Transaction_Layer_Repository;
import com.fin.bancs.services.Core_Transaction_services;
import com.fin.bancs.transactions.Core_Transaction_Layer;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.*;

import java.nio.charset.CoderResult;
import java.util.List;
import java.util.Optional;

@Tag(name= "Omega Bank transaction Controller",
description= "This collection contains CRUD operation for transactions")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private Core_Transaction_Layer_Repository repo;
    @Autowired
    private  Core_Transaction_services txnServices;

    //Test application
    @Operation(summary ="This Endpoint handle creation of transaction ",
    description= "To create Cash and transfer type of Transaction")
    @ApiResponse(
        responseCode= "201",
        description = "http status CREATED"

    )
    @PostMapping("/cash")
    public String createTxn(@RequestBody List<Core_Transaction_Layer> centralTransaction){
    	//capture the input for cash transaction(credit and debit type)
    	Core_Transaction_services txn_service= new Core_Transaction_services();
    	//txn_service.CreateTransaction();
    	//repo.save(centralTransaction);
        return "SuccessFully Inserted";
    }

    @Operation(summary= "This end point is to create transfer type transaction",
    description= "to create transfer type txn")
    @PostMapping("/transfer")
    public String transferTxn (@RequestBody List<Core_Transaction_Layer> txnInput){
        //txnServices.CreateTransaction(txnInput);
        return "Transaction is successful";
    }
    @Operation(
        summary = "This Endpoint enquiry the transactions ",
        description= "This endpoint handle enquiry for existing transaction stored in DB"
    )
    //Transaction  with TXN-ID 
    @GetMapping("/getByTxnId")
    public Optional<Core_Transaction_Layer> getTxn(@RequestParam Integer txnId){
        //Central_Transaction central_transaction= repo.findById(Central_Transaction.class,txnId);
        Optional<Core_Transaction_Layer> central_transaction= repo.findById(txnId);

        return central_transaction;
    }

}
