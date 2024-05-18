package com.fin.bancs.services;

import com.fin.bancs.TM.Transfer_Transaction;
import com.fin.bancs.transactions.Central_Transaction;
import com.fin.bancs.transactions.Transaction_Out;
import org.springframework.beans.factory.annotation.Autowired;

import com.fin.bancs.repository.Central_Transaction_Repository;
import com.fin.bancs.transactions.Transaction_Input;

import java.util.List;

public class Central_Transaction_services {
	
	@Autowired
	private Central_Transaction_Repository  cenTxnRepo;

	
	public void CreateTransaction(List<Central_Transaction> txnInput) {
		Transaction_Out transactionOut_dr = new Transaction_Out();
		Transaction_Out transactionOut_cr = new Transaction_Out();
		Central_Transaction central_transaction_cash= new Central_Transaction();
		Central_Transaction central_transaction_cr= new Central_Transaction();
		Central_Transaction central_transaction_dr= new Central_Transaction();
		List<Central_Transaction> central_transaction = (List<Central_Transaction>) new Central_Transaction();
		//Check Transaction Type Cash/transfer
		for(Central_Transaction txn:txnInput){
			if(txn.getTXN_TYPE() == 0){
				//handle Error for Transaction type not Specified
			}
			if (txn.getTXN_TYPE()==1){
				central_transaction_cash= txn;
			}
			if (txn.getTXN_TYPE()==2) {
				if(txn.getCredit_debit_flag()==1) //Credit
				{
					central_transaction_cr= txn;
				}
				if(txn.getCredit_debit_flag()==2) //Debit
				{
					central_transaction_dr= txn;
				}

			}
		}

		if(central_transaction_cash.getTXN_TYPE()==1) //Cash-> do cash Operation
		{
			//1. Debit from source account and credit to internal Credit Account
			//one row can handle this txn




			String str= String.valueOf(central_transaction_cash.getACCOUNT_ID_DR());
			central_transaction_cash.setTXN_DESC(str+ " - " + "cash-Transaction");
			cenTxnRepo.save(central_transaction_cash);
		}
		if(central_transaction_cr.getTXN_TYPE()==2){  //Transfer-> Do transfer Operation

			//1. Debit from source account and credit to internal Credit Account
			String str_dr= String.valueOf(central_transaction_cr.getACCOUNT_ID_DR());
			central_transaction_cr.setTXN_DESC(str_dr+ " - " + "Transfer-Transaction");




			//1. Debit from internal Debit Account  and credit to Target account

			String str_cr= String.valueOf(central_transaction_dr.getACCOUNT_ID_CR());
			central_transaction_dr.setTXN_DESC(str_cr+ " - " + "Transfer-Transaction");

		}
		central_transaction.add(central_transaction_cr);
		central_transaction.add(central_transaction_dr);

		cenTxnRepo.saveAll(central_transaction);

		
	}
	
}
