package com.fin.bancs.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.GlobalException;
import com.fin.bancs.error.ResourceNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fin.bancs.AM.Account;
import com.fin.bancs.AM.AccountPk;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.repository.Core_Transaction_Layer_Repository;
import com.fin.bancs.transactions.Core_Transaction_Layer;
import com.fin.bancs.transactions.Transaction_Out;

public class Core_Transaction_services {

	private static final Logger log = LogManager.getLogger(Core_Transaction_services.class);
	@Autowired
	private Core_Transaction_Layer_Repository  coreRepo;
	@Autowired
	private Account_repository accRepo;

    public Core_Transaction_services(Core_Transaction_Layer_Repository coreRepo, Account_repository accRepo) {
        this.coreRepo = coreRepo;
        this.accRepo = accRepo;
    }


    public void CreateTransaction(List<Core_Transaction_Layer> txnInput) {
		Core_Transaction_Layer core_transaction_cash= new Core_Transaction_Layer();
		Core_Transaction_Layer core_transaction_cr= new Core_Transaction_Layer();
		Core_Transaction_Layer core_transaction_dr= new Core_Transaction_Layer();
		List<Core_Transaction_Layer> core_transaction = new ArrayList<>();
		List<Account> UpdateAccBal= new ArrayList<>();
		GlobalException Exception = new GlobalException();
		//Check Transaction Type Cash/transfer
		for(Core_Transaction_Layer txn:txnInput){
			if(txn.getTXN_TYPE() == 0){
				//handle Error for Transaction type not Specified
			}
			if (txn.getTXN_TYPE()==1){
				core_transaction_cash= txn;
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
			Core_Transaction_Layer coreTxn_cash_dr= new Core_Transaction_Layer();
			Core_Transaction_Layer coreTxn_cash_cr = new Core_Transaction_Layer();
			//1. Debit from source account and credit to internal Credit Account
			//Find out Credit side account details and debit side account details.
			//one row can handle this txn
			Account acc = new Account();

			Optional<Account> acc_internal= Optional.of(new  Account());
			Optional<Account> acc_cash= Optional.of(new  Account());
			AccountPk accPk= new AccountPk();
			BigDecimal total_amt_internal;
			BigDecimal total_amt_cash;
			//debit/credit to internal account srarts
			accPk.setACCOUNT_ID(core_transaction_cash.getACCOUNT_ID_CR()); //internal Account need to maintain internally
			accPk.setACCOUNT_TYPE(1); //For inernal account
			acc_internal= accRepo.findById(accPk);
			if(acc_internal.isEmpty()) {
				Exception.handleResourceNotFoundException(
						new ResourceNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND));
			}else {
			acc= acc_internal.get();
			BigDecimal Available_AMT= acc.getAVAILABLE_BALANCE();
			BigDecimal transaction_amt= core_transaction_cash.getTXN_AMT();
			int cred_deb_flag= core_transaction_cash.getCredit_debit_flag();
			if(cred_deb_flag==1) {
				total_amt_internal= Available_AMT.add(transaction_amt.multiply(new BigDecimal(1)));
			}else {
				total_amt_internal= Available_AMT.add(transaction_amt.multiply(new BigDecimal(-1)));
			}
			acc.setAVAILABLE_BALANCE(total_amt_internal);
			//Core Txn for internal Account Started here
				coreTxn_cash_cr.setACCOUNT_ID_CR(core_transaction_cash.getACCOUNT_ID_CR());
				coreTxn_cash_cr.setACCOUNT_TYPE_CR(1);
				coreTxn_cash_cr.setTXN_AMT(core_transaction_cash.getTXN_AMT());
				coreTxn_cash_cr.setCredit_debit_flag(1);
				coreTxn_cash_cr.setTXN_TYPE(1);
				coreTxn_cash_cr.setTXN_SEQ(1); //use sequence generator in case need to write txn with different cases
				coreTxn_cash_dr.setTXN_DESC("cash-transaction: Credit");
				coreTxn_cash_cr.setGEN_DT(core_transaction_cash.getGEN_DT());
				coreTxn_cash_cr.setACCOUNT_ID_DR(core_transaction_cash.getACCOUNT_ID_DR());
				coreTxn_cash_cr.setACCOUNT_TYPE_DR(core_transaction_cr.getACCOUNT_TYPE_DR());

			coreRepo.save(coreTxn_cash_cr);
				//Core Txn Ends here
			accRepo.save(acc);
			//DEBIT/credit to internal account ends

			}
			
			//For customer account
			//Debit/credit to Customer account ends
			accPk.setACCOUNT_ID(core_transaction_cash.getACCOUNT_ID_DR());
			accPk.setACCOUNT_TYPE(core_transaction_cash.getACCOUNT_TYPE_DR());
			acc_cash= accRepo.findById(accPk);
			if(acc_cash.isEmpty()) {
				//Handle it account not found Error
				log.debug("No Data Found");
			}else {
			acc= acc_cash.get(); 
			BigDecimal Available_AMT= acc.getAVAILABLE_BALANCE();
			BigDecimal transaction_amt= core_transaction_cash.getTXN_AMT();
			int cred_deb_flag= core_transaction_cash.getCredit_debit_flag();
			if(cred_deb_flag==1) {
				total_amt_cash= Available_AMT.add(transaction_amt.multiply(new BigDecimal(1)));
			}else {
				total_amt_cash= Available_AMT.add(transaction_amt.multiply(new BigDecimal(-1)));
			}
			acc.setAVAILABLE_BALANCE(total_amt_cash);

				//Core Txn for cash Account Started here
				coreTxn_cash_dr.setACCOUNT_ID_CR(core_transaction_cash.getACCOUNT_ID_CR());
				coreTxn_cash_dr.setACCOUNT_TYPE_CR(core_transaction_cr.getACCOUNT_TYPE_CR());
				coreTxn_cash_dr.setTXN_AMT(core_transaction_cash.getTXN_AMT());
				coreTxn_cash_dr.setCredit_debit_flag(2);
				coreTxn_cash_dr.setACCOUNT_ID_DR(core_transaction_cash.getACCOUNT_ID_DR());
				coreTxn_cash_dr.setTXN_TYPE(core_transaction_cr.getACCOUNT_TYPE_DR());
				//coreTxn_cash_cr.setTXN_SEQ(2); //use auto sequence generator in case need to write txn with different cases
				coreTxn_cash_dr.setGEN_DT(core_transaction_cash.getGEN_DT());
				coreTxn_cash_dr.setTXN_DESC("cash-transaction: Debit");
				coreTxn_cash_dr.setACCOUNT_ID_DR(core_transaction_cr.getACCOUNT_ID_DR());

				coreRepo.save(coreTxn_cash_dr);
				//Core Txn Ends here
			accRepo.save(acc);
			//Debit/credit to Customer account ends
			}

			accPk.setACCOUNT_ID(core_transaction_cash.getACCOUNT_ID_DR());
			accPk.setACCOUNT_TYPE(core_transaction_cash.getACCOUNT_TYPE_DR());
			acc_cash = accRepo.findById(accPk);
			if(acc_cash.isEmpty()) {
				//Handle it account not found Error
				log.debug("No Data Found");
			}
			acc=acc_cash.get();


			String str= String.valueOf(core_transaction_cash.getACCOUNT_ID_DR());
			core_transaction_cash.setTXN_DESC(str+ " - " + "cash-Transaction");
			coreRepo.save(core_transaction_cash);
		}
		
		
		
		if(core_transaction_cr.getTXN_TYPE()==2){  //Transfer-> Do transfer Operation
			// total 4 transaction row will be written
			//1. Debit from source account 2. credit to internal Credit Account
			Core_Transaction_Layer coreTransactionLayer_int_dr = new Core_Transaction_Layer();
			Core_Transaction_Layer coreTransactionLayer_int_cr = new Core_Transaction_Layer();
			Core_Transaction_Layer coreTransactionLayer_acc_cr = new Core_Transaction_Layer();
			Core_Transaction_Layer coreTransactionLayer_acc_dr = new Core_Transaction_Layer();
			//case1 -- Debit from source account
			AccountPk cash_acc_pk= new AccountPk();
			AccountPk internal_acc_pk= new AccountPk();
			Account cash_acc_dr= new Account();
			Account cash_acc_cr= new Account();
			Account internal_acc_cr= new Account();
			Account internal_acc_dr= new Account();
			//Account balance Update Starts here
			cash_acc_pk.setACCOUNT_ID(core_transaction_dr.getACCOUNT_ID_DR());
			cash_acc_pk.setACCOUNT_TYPE(core_transaction_dr.getACCOUNT_TYPE_DR());
			Optional<Account> account_cash_dr = accRepo.findById(cash_acc_pk);
			if(account_cash_dr.isEmpty()) {
				//handle Account Not Found Error
				log.debug("No Data Found");
			}else{
				cash_acc_dr= account_cash_dr.get();
				cash_acc_dr.setAVAILABLE_BALANCE(cash_acc_dr.getAVAILABLE_BALANCE().subtract(core_transaction_dr.getTXN_AMT()));
				UpdateAccBal.add(cash_acc_dr);
			}
			internal_acc_pk.setACCOUNT_ID(18626); //Cr type internal Account
			internal_acc_pk.setACCOUNT_TYPE(1);
			Optional<Account> int_acc_cr= accRepo.findById(internal_acc_pk);
			if(int_acc_cr.isEmpty()){
				//handle Not Found Exception
				log.debug("No Data Found");
			}else {
				internal_acc_cr= int_acc_cr.get();
				internal_acc_cr.setAVAILABLE_BALANCE(internal_acc_dr.getAVAILABLE_BALANCE().add(core_transaction_dr.getTXN_AMT()));
				UpdateAccBal.add(internal_acc_cr);
			}
			//Account balance Update Ends here
            coreTransactionLayer_acc_dr.setACCOUNT_ID_DR(core_transaction_dr.getACCOUNT_ID_DR());
				coreTransactionLayer_acc_dr.setACCOUNT_TYPE_DR(core_transaction_dr.getACCOUNT_TYPE_DR());
				coreTransactionLayer_acc_dr.setTXN_AMT(core_transaction_dr.getTXN_AMT());
				coreTransactionLayer_acc_dr.setGEN_DT(core_transaction_dr.getGEN_DT());
				coreTransactionLayer_acc_dr.setCredit_debit_flag(2);
				coreTransactionLayer_acc_dr.setCURRENCY("INR");
				coreTransactionLayer_acc_dr.setTXN_DESC(core_transaction_dr.getTXN_DESC());
			core_transaction.add(coreTransactionLayer_acc_dr);
			//case2 -- Credit to internal Account
				coreTransactionLayer_int_cr.setTXN_AMT(core_transaction_dr.getTXN_AMT());
				coreTransactionLayer_int_cr.setGEN_DT(core_transaction_dr.getGEN_DT());
				coreTransactionLayer_int_cr.setTXN_DESC(core_transaction_dr.getTXN_DESC());
				coreTransactionLayer_int_cr.setCredit_debit_flag(1);
				coreTransactionLayer_int_cr.setTXN_DESC(core_transaction_dr.getTXN_DESC());
				coreTransactionLayer_int_cr.setTXN_DESC(core_transaction_dr.getTXN_DESC());
				coreTransactionLayer_int_cr.setCURRENCY("INR");
			core_transaction.add(coreTransactionLayer_int_cr);



			//Account balance Update Starts here
			cash_acc_pk.setACCOUNT_ID(core_transaction_dr.getACCOUNT_ID_CR());
			cash_acc_pk.setACCOUNT_TYPE(core_transaction_dr.getACCOUNT_TYPE_CR());
			Optional<Account> account_cash_cr = accRepo.findById(cash_acc_pk);
			if(account_cash_cr.isEmpty()) {
				//handle Account Not Found Error
				log.debug("No Data Found");
			}else{
				cash_acc_cr= account_cash_cr.get();
				cash_acc_cr.setAVAILABLE_BALANCE(cash_acc_cr.getAVAILABLE_BALANCE().add(core_transaction_dr.getTXN_AMT()));
				UpdateAccBal.add(cash_acc_cr);
			}
			internal_acc_pk.setACCOUNT_ID(18625); //requires rules to get Internal Account for cr/dr type
			internal_acc_pk.setACCOUNT_TYPE(1);
			Optional<Account> int_acc_dr= accRepo.findById(internal_acc_pk);
			if(int_acc_dr.isEmpty()){
				//handle Not Found Exception
				log.debug("No Data Found");
			}else {
				internal_acc_dr= int_acc_cr.get();
				internal_acc_dr.setAVAILABLE_BALANCE(internal_acc_dr.getAVAILABLE_BALANCE().subtract(core_transaction_dr.getTXN_AMT()));
				UpdateAccBal.add(internal_acc_dr);
			}
			//Account balance Update Ends here

			//case3 -- Debit from Internal account
			coreTransactionLayer_int_dr.setACCOUNT_ID_DR(core_transaction_dr.getACCOUNT_ID_DR()); //Debit type of internal account
			coreTransactionLayer_int_dr.setACCOUNT_TYPE_DR(core_transaction_dr.getACCOUNT_TYPE_DR());
			coreTransactionLayer_int_dr.setTXN_AMT(core_transaction_dr.getTXN_AMT());
			coreTransactionLayer_int_dr.setGEN_DT(core_transaction_dr.getGEN_DT());
			coreTransactionLayer_int_dr.setCredit_debit_flag(2);
			coreTransactionLayer_int_dr.setCURRENCY("INR");
			coreTransactionLayer_int_dr.setTXN_DESC(core_transaction_dr.getTXN_DESC());
			core_transaction.add(coreTransactionLayer_int_dr);
			//case4 -- Credit to Target Account
			coreTransactionLayer_acc_cr.setTXN_AMT(core_transaction_dr.getTXN_AMT());
			coreTransactionLayer_acc_cr.setGEN_DT(core_transaction_dr.getGEN_DT());
			coreTransactionLayer_acc_cr.setTXN_DESC(core_transaction_dr.getTXN_DESC());
			coreTransactionLayer_acc_cr.setCredit_debit_flag(1);
			coreTransactionLayer_acc_cr.setTXN_DESC(core_transaction_dr.getTXN_DESC());
			coreTransactionLayer_acc_cr.setTXN_DESC(core_transaction_dr.getTXN_DESC());
			coreTransactionLayer_acc_cr.setCURRENCY("INR");
			core_transaction.add(coreTransactionLayer_acc_cr);
		}
		accRepo.saveAll(UpdateAccBal);
		coreRepo.saveAll(core_transaction);
	}

}
