package com.fin.bancs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fin.bancs.AM.Account;
import com.fin.bancs.AM.AccountPk;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.repository.Core_Transaction_Layer_Repository;
import com.fin.bancs.transactions.Core_Transaction_Layer;
import com.fin.bancs.transactions.Transaction_Out;

public class Core_Transaction_services {
	
	@Autowired
	private Core_Transaction_Layer_Repository  cenTxnRepo;
	@Autowired
	private Account_repository accRepo;

	
	public void CreateTransaction(List<Core_Transaction_Layer> txnInput) {
		Transaction_Out transactionOut_dr = new Transaction_Out();
		Transaction_Out transactionOut_cr = new Transaction_Out();
		Core_Transaction_Layer core_transaction_cash= new Core_Transaction_Layer();
		Core_Transaction_Layer core_transaction_cr= new Core_Transaction_Layer();
		Core_Transaction_Layer core_transaction_dr= new Core_Transaction_Layer();
		List<Core_Transaction_Layer> core_transaction = (List<Core_Transaction_Layer>) new Core_Transaction_Layer();
		//Check Transaction Type Cash/transfer
		for(Core_Transaction_Layer txn:txnInput){
			if(txn.getTXN_TYPE() == 0){
				//handle Error for Transaction type not Specified
			}
			if (txn.getTXN_TYPE()==1){
				core_transaction_cash= txn;
				cenTxnRepo.save(core_transaction_cash);
			}
			if (txn.getTXN_TYPE()==2) {
				if(txn.getCredit_debit_flag()==1) //Credit
				{
					core_transaction_cr= txn;
				}
				if(txn.getCredit_debit_flag()==2) //Debit
				{
					core_transaction_dr= txn;
				}

			}
		}

		if(core_transaction_cash.getTXN_TYPE()==1) //Cash-> do cash Operation
		{
			//1. Debit from source account and credit to internal Credit Account
			//Find out Credit side account details and debit side account details.
			//one row can handle this txn
			Optional<Account> acc_internal= Optional.of(new  Account());
			Optional<Account> acc_cash= Optional.of(new  Account());
			AccountPk accpk= new AccountPk();
			//credit to internal account
			accpk.setACCOUNT_ID(core_transaction_cash.getACCOUNT_ID_CR());
			accpk.setACCOUNT_TYPE(1); //For inernal account
			acc_internal= accRepo.findById(accpk);
			if(acc_internal == null) {
				//Handle it account not found Error 
			}
			accpk.setACCOUNT_ID(core_transaction_cash.getACCOUNT_ID_DR());
			accpk.setACCOUNT_TYPE(core_transaction_cash.getACCOUNT_TYPE_DR());
			acc_cash = accRepo.findById(accpk);
			if(acc_cash == null) {
				//Handle it account not found Error 
			}

			String str= String.valueOf(core_transaction_cash.getACCOUNT_ID_DR());
			core_transaction_cash.setTXN_DESC(str+ " - " + "cash-Transaction");
			cenTxnRepo.save(core_transaction_cash);
		}
		if(core_transaction_cr.getTXN_TYPE()==2){  //Transfer-> Do transfer Operation

			//1. Debit from source account and credit to internal Credit Account
			String str_dr= String.valueOf(core_transaction_cr.getACCOUNT_ID_DR());
			core_transaction_cr.setTXN_DESC(str_dr+ " - " + "Transfer-Transaction");




			//1. Debit from internal Debit Account  and credit to Target account

			String str_cr= String.valueOf(core_transaction_dr.getACCOUNT_ID_CR());
			core_transaction_dr.setTXN_DESC(str_cr+ " - " + "Transfer-Transaction");

		}
		core_transaction.add(core_transaction_cr);
		core_transaction.add(core_transaction_dr);

		cenTxnRepo.saveAll(core_transaction);

		
	}
	
}
