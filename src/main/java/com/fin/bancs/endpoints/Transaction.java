package com.fin.bancs.endpoints;

import com.fin.bancs.repository.Central_Transaction_Repository;
import com.fin.bancs.transactions.Central_Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class Transaction {
    @Autowired
    private Central_Transaction_Repository repo;


    @PostMapping("/cash")
    public String createTxn(@RequestBody Central_Transaction centralTransaction){
    	repo.save(centralTransaction);
        return "SuccessFully Inserted";
    }
    @GetMapping("/get/{txnId}")
    public Optional<Central_Transaction> getTxn(@PathVariable Integer txnId){
        //Central_Transaction central_transaction= repo.findById(Central_Transaction.class,txnId);
        Optional<Central_Transaction> central_transaction= repo.findById(txnId);

        return central_transaction;
    }

}
