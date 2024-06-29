package com.fin.bancs.endpoints;

import com.fin.bancs.repository.Core_Transaction_Layer_Repository;
import com.fin.bancs.transactions.Core_Transaction_Layer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class Transaction {
    @Autowired
    private Core_Transaction_Layer_Repository repo;

    //Test application
    @PostMapping("/cash")
    public String createTxn(@RequestBody Core_Transaction_Layer centralTransaction){
    	//capture the input for cash transaction(credit and debit type)
    	repo.save(centralTransaction);
        return "SuccessFully Inserted";
    }
    //Transaction  with TXN-ID 
    @GetMapping("/get/{txnId}")
    public Optional<Core_Transaction_Layer> getTxn(@PathVariable Integer txnId){
        //Central_Transaction central_transaction= repo.findById(Central_Transaction.class,txnId);
        Optional<Core_Transaction_Layer> central_transaction= repo.findById(txnId);

        return central_transaction;
    }

}
