package com.fin.bancs.controller;

import com.fin.bancs.repository.Core_Transaction_Layer_Repository;
import com.fin.bancs.services.Core_Transaction_services;
import com.fin.bancs.transactions.Core_Transaction_Layer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@tags(summary= "Omega Bank transaction Controller",
description= "This collection contains CRUD operation for transactions")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private Core_Transaction_Layer_Repository repo;

    //Test application
    @Operation(summery ="This Endpoint handle creation of transaction ",
    description= "To create Cash and transfer type of Transaction")
    @ApiResponse(
        responseCode= "201",
        description = "http status CREATED"

    )
    @PostMapping("/cash")
    public String createTxn(@RequestBody List<Core_Transaction_Layer> centralTransaction){
    	//capture the input for cash transaction(credit and debit type)
    	Core_Transaction_services txn_service= new Core_Transaction_services();
    	txn_service.CreateTransaction(centralTransaction);
    	//repo.save(centralTransaction);
        return "SuccessFully Inserted";
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
