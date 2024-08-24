package com.fin.bancs.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.mapper.TransactionMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fin.bancs.account.Account;
import com.fin.bancs.account.AccountPk;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.repository.Core_Transaction_Repository;
import com.fin.bancs.transactions.CashTransactionInput;
import com.fin.bancs.transactions.CashTransactionResponse;
import com.fin.bancs.transactions.Core_Transaction;
import com.fin.bancs.utils.SequenceGenerator;

@Service
public class Core_Transaction_services  {

	private static final Logger log = LogManager.getLogger(Core_Transaction_services.class);
	@Autowired
	private Core_Transaction_Repository  coreRepo;
	@Autowired
	private Account_repository accRepo;
	@Autowired
	SequenceGenerator seqGen;

    public Core_Transaction_services(Core_Transaction_Repository coreRepo, Account_repository accRepo) {
        this.coreRepo = coreRepo;
        this.accRepo = accRepo;
    }
    
    public Core_Transaction_services() {
		super();
	}




    public void CreateTransaction(TransactionDTO txnInputDTO) {
		Core_Transaction core_transaction_cash= new Core_Transaction();
		Core_Transaction core_transaction_cr= new Core_Transaction();
		Core_Transaction core_transaction_dr= new Core_Transaction();
		List<Core_Transaction> core_transaction = new ArrayList<>();
		List<Account> UpdateAccBal= new ArrayList<>();

		//for(Core_Transaction_Layer txn:txnInput)
		Core_Transaction txn = TransactionMapper.mapTOCoreTxnLayer(txnInputDTO,new Core_Transaction());
		//txn =txnInput;
		{
			if(txn.getTxn_type() == 0){
				//handle Error for Transaction type not Specified
			}
			if (txnInputDTO.getTxnType()==1){
				core_transaction_cash= txn;
			}
			if (txn.getTxn_type()==2) {
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
		
		if(core_transaction_cr.getTxn_type()==2) {  //Transfer-> Do transfer Operation
			// total 4 transaction row will be written
			//1. Debit from source account 2. credit to internal Credit Account
			Core_Transaction coreTransactionLayer_int_dr = new Core_Transaction();
			Core_Transaction coreTransactionLayer_int_cr = new Core_Transaction();
			Core_Transaction coreTransactionLayer_acc_cr = new Core_Transaction();
			Core_Transaction coreTransactionLayer_acc_dr = new Core_Transaction();
			//case1 -- Debit from source account
			AccountPk cash_acc_pk = new AccountPk();
			AccountPk internal_acc_pk = new AccountPk();
			Account cash_acc_dr = new Account();
			Account cash_acc_cr = new Account();
			Account internal_acc_cr = new Account();
			Account internal_acc_dr = new Account();
			//Account balance Update Starts here
			cash_acc_pk.setAccount_id(core_transaction_dr.getAccount_id_dr());
			cash_acc_pk.setAccount_type(core_transaction_dr.getAccount_type_dr());
			Optional<Account> account_cash_dr = accRepo.findById(cash_acc_pk);
			if (account_cash_dr.isEmpty()) {
				//handle Account Not Found Error
				log.debug("No Data Found");
			} else {
				cash_acc_dr = account_cash_dr.get();
				cash_acc_dr.setAvailable_balance(cash_acc_dr.getAvailable_balance().subtract(core_transaction_dr.getTxn_amt()));
				UpdateAccBal.add(cash_acc_dr);
			}
			internal_acc_pk.setAccount_id(18626); //Cr type internal Account
			internal_acc_pk.setAccount_type(1);
			Optional<Account> int_acc_cr = accRepo.findById(internal_acc_pk);
			if (int_acc_cr.isEmpty()) {
				//handle Not Found Exception
				log.debug("No Data Found");
			} else {
				internal_acc_cr = int_acc_cr.get();
				internal_acc_cr.setAvailable_balance(internal_acc_dr.getAvailable_balance().add(core_transaction_dr.getTxn_amt()));
				UpdateAccBal.add(internal_acc_cr);
			}
			//Account balance Update Ends here
			if (int_acc_cr.isEmpty() && account_cash_dr.isPresent()) {
				coreTransactionLayer_acc_dr.setAccount_id_dr(core_transaction_dr.getAccount_id_dr());
				coreTransactionLayer_acc_dr.setAccount_type_dr(core_transaction_dr.getAccount_type_dr());
				coreTransactionLayer_acc_dr.setTxn_amt(core_transaction_dr.getTxn_amt());
				coreTransactionLayer_acc_dr.setGen_dt(core_transaction_dr.getGen_dt());
				coreTransactionLayer_acc_dr.setCredit_debit_flag(2);
				coreTransactionLayer_acc_dr.setCurrency("INR");
				coreTransactionLayer_acc_dr.setTxn_desc(core_transaction_dr.getTxn_desc());
				core_transaction.add(coreTransactionLayer_acc_dr);
				//case2 -- Credit to internal Account
				coreTransactionLayer_int_cr.setTxn_amt(core_transaction_dr.getTxn_amt());
				coreTransactionLayer_int_cr.setGen_dt(core_transaction_dr.getGen_dt());
				coreTransactionLayer_int_cr.setTxn_desc(core_transaction_dr.getTxn_desc());
				coreTransactionLayer_int_cr.setCredit_debit_flag(1);
				coreTransactionLayer_int_cr.setTxn_desc(core_transaction_dr.getTxn_desc());
				coreTransactionLayer_int_cr.setTxn_desc(core_transaction_dr.getTxn_desc());
				coreTransactionLayer_int_cr.setCurrency("INR");
				core_transaction.add(coreTransactionLayer_int_cr);


				//Account balance Update Starts here
				cash_acc_pk.setAccount_id(core_transaction_dr.getAccount_id_cr());
				cash_acc_pk.setAccount_type(core_transaction_dr.getAccount_type_cr());
				Optional<Account> account_cash_cr = accRepo.findById(cash_acc_pk);
				if (account_cash_cr.isEmpty()) {
					//handle Account Not Found Error
					log.debug("No Data Found");
				} else {
					cash_acc_cr = account_cash_cr.get();
					cash_acc_cr.setAvailable_balance(cash_acc_cr.getAvailable_balance().add(core_transaction_dr.getTxn_amt()));
					UpdateAccBal.add(cash_acc_cr);
				}
				internal_acc_pk.setAccount_id(18625); //requires rules to get Internal Account for cr/dr type
				internal_acc_pk.setAccount_type(1);
				Optional<Account> int_acc_dr = accRepo.findById(internal_acc_pk);
				if (int_acc_dr.isEmpty()) {
					//handle Not Found Exception
					log.debug("No Data Found");
				} else {
					internal_acc_dr = int_acc_cr.get();
					internal_acc_dr.setAvailable_balance(internal_acc_dr.getAvailable_balance().subtract(core_transaction_dr.getTxn_amt()));
					UpdateAccBal.add(internal_acc_dr);
				}
				//Account balance Update Ends here

				//case3 -- Debit from Internal account
				coreTransactionLayer_int_dr.setAccount_id_dr(core_transaction_dr.getAccount_id_dr()); //Debit type of internal account
				coreTransactionLayer_int_dr.setAccount_type_dr(core_transaction_dr.getAccount_type_dr());
				coreTransactionLayer_int_dr.setTxn_amt(core_transaction_dr.getTxn_amt());
				coreTransactionLayer_int_dr.setGen_dt(core_transaction_dr.getGen_dt());
				coreTransactionLayer_int_dr.setCredit_debit_flag(2);
				coreTransactionLayer_int_dr.setCurrency("INR");
				coreTransactionLayer_int_dr.setTxn_desc(core_transaction_dr.getTxn_desc());
				core_transaction.add(coreTransactionLayer_int_dr);
				//case4 -- Credit to Target Account
				coreTransactionLayer_acc_cr.setTxn_amt(core_transaction_dr.getTxn_amt());
				coreTransactionLayer_acc_cr.setGen_dt(core_transaction_dr.getGen_dt());
				coreTransactionLayer_acc_cr.setTxn_desc(core_transaction_dr.getTxn_desc());
				coreTransactionLayer_acc_cr.setCredit_debit_flag(1);
				coreTransactionLayer_acc_cr.setTxn_desc(core_transaction_dr.getTxn_desc());
				coreTransactionLayer_acc_cr.setTxn_desc(core_transaction_dr.getTxn_desc());
				coreTransactionLayer_acc_cr.setCurrency("INR");
				core_transaction.add(coreTransactionLayer_acc_cr);
			}
		}
		accRepo.saveAll(UpdateAccBal);
		coreRepo.saveAll(core_transaction);
	}

	public String cashTransaction(CashTransactionInput txnInput){
		String txnRef="";
		if(txnInput.getTxnType() == 1 ) //Cash-> do cash Operation
		{
			Core_Transaction coreTxn_cash_cash= new Core_Transaction();
			Core_Transaction coreTxn_cash_internal = new Core_Transaction();
			//1. Debit from source account and credit to internal Credit Account
			//Find out Credit side account details and debit side account details.
			//one row can handle this txn
			Account acc = new Account();
			Optional<Account> acc_internal= Optional.of(new  Account());
			Optional<Account> acc_cash= Optional.of(new  Account());
			AccountPk accPk= new AccountPk();
			AccountPk accID= new AccountPk();
			BigDecimal total_amt_internal;
			BigDecimal total_amt_cash;
			//debit/credit to internal account srarts
			if(txnInput.getCreditDebitFlag() == 2) {
				//accPk.setAccount_id(core_transaction_cash.getAccount_id_cr()); //internal Account need to maintain internally
				accID.setAccount_id(111111122); //Default credit Account
				accID.setAccount_type(1); //For inernal account
			}else {
				accID.setAccount_id(111111123); //Default debit Account
				accID.setAccount_type(1); //For inernal account
			}
			acc_internal= accRepo.findById(accID);
			accPk.setAccount_id(txnInput.getAccountId());
			accPk.setAccount_type(txnInput.getAccountType());
			acc_cash= accRepo.findById(accPk);
			if(acc_internal.isEmpty() || acc_cash.isEmpty()) {
				throw new ResourceNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND);
			}else {
				acc= acc_internal.get();
				BigDecimal Available_AMT= acc.getAvailable_balance();
				BigDecimal transaction_amt= txnInput.getTxnAmt();
				int cred_deb_flag= txnInput.getCreditDebitFlag();
				if(cred_deb_flag == 1) {
					total_amt_internal= Available_AMT.add(transaction_amt.multiply(new BigDecimal(-1)));
				}else {
					total_amt_internal= Available_AMT.add(transaction_amt.multiply(new BigDecimal(1)));
				}
				acc.setAvailable_balance(total_amt_internal);
				//Core Txn for internal Account Started here
				//coreTxn_cash_cr = TransactionMapper.mapTOCoreTxnLayer(txnInput, new Core_Transaction() );
				if(coreTxn_cash_internal.getCredit_debit_flag() == 1) {
					coreTxn_cash_internal.setTxn_desc(txnInput.getTxnDesc() + " Internal : DEBIT");
					coreTxn_cash_internal.setAccount_id_dr(acc.getAccountId().getAccount_id());
					coreTxn_cash_internal.setAccount_type_dr(acc.getAccountId().getAccount_type());
					coreTxn_cash_internal.setCredit_debit_flag(2);
				}else {
					coreTxn_cash_internal.setTxn_desc(txnInput.getTxnDesc() + " Internal : CREDIT");
					coreTxn_cash_internal.setAccount_id_cr(acc.getAccountId().getAccount_id());
					coreTxn_cash_internal.setAccount_type_cr(acc.getAccountId().getAccount_type());
					coreTxn_cash_internal.setCredit_debit_flag(1);
				}
				coreTxn_cash_internal.setTxn_amt(txnInput.getTxnAmt());
				coreTxn_cash_internal.setGen_dt(LocalDate.now());
				coreTxn_cash_internal.setCurrency(txnInput.getCurrency());
				coreTxn_cash_internal.setTxn_seq(1);
				BigInteger txnid = seqGen.generateSequence("TxnSeq");
				txnRef = "1-" + txnid.toString();
				coreTxn_cash_internal.setTxnRefId(txnRef);
				coreRepo.save(coreTxn_cash_internal);
				//Core Txn Ends here
				accRepo.save(acc);
				//DEBIT/credit to internal account ends

			//}

			//For customer account
			//Debit/credit to Customer account ends
			//{
				acc= acc_cash.get();
				BigDecimal Available_AMT_cash= acc.getAvailable_balance();
				BigDecimal transaction_amt_cash= txnInput.getTxnAmt();
				if(txnInput.getCreditDebitFlag()==1) {
					total_amt_cash= Available_AMT_cash.add(transaction_amt.multiply(new BigDecimal(1)));
				}else {
					total_amt_cash= Available_AMT_cash.add(transaction_amt_cash.multiply(new BigDecimal(-1)));
				}
				acc.setAvailable_balance(total_amt_cash);

				//Core Txn for cash Account Started here
				//coreTxn_cash_dr= TransactionMapper.mapTOCoreTxnLayer(txnInput, new Core_Transaction());
				//coreTxn_cash_cr.setTXN_SEQ(2); //use auto sequence generator in case need to write txn with different cases
				coreTxn_cash_cash.setGen_dt(LocalDate.now());
				if(txnInput.getCreditDebitFlag() == 1) {
					coreTxn_cash_cash.setTxn_desc(txnInput.getTxnDesc() + " cash : Credit");
					coreTxn_cash_cash.setAccount_id_cr(acc.getAccountId().getAccount_id());
					coreTxn_cash_cash.setAccount_type_cr(acc.getAccountId().getAccount_type());
					coreTxn_cash_cash.setCredit_debit_flag(1);
				}else {
					coreTxn_cash_cash.setTxn_desc(txnInput.getTxnDesc() + " cash : Debit");
					coreTxn_cash_cash.setAccount_id_dr(acc.getAccountId().getAccount_id());
					coreTxn_cash_cash.setAccount_type_dr(acc.getAccountId().getAccount_type());
					coreTxn_cash_cash.setCredit_debit_flag(2);
				}
				coreTxn_cash_cash.setTxn_amt(txnInput.getTxnAmt());
				coreTxn_cash_cash.setGen_dt(LocalDate.now());
				coreTxn_cash_cash.setTxn_seq(2);
				coreTxn_cash_cash.setTxnRefId(txnRef);

				coreRepo.save(coreTxn_cash_cash);
				//Core Txn Ends here
				accRepo.save(acc);
				//Debit/credit to Customer account ends
			}
		}
		return txnRef;
	}
	public List<TransactionDTO> getTransactionDetails(String txnId){
		List<Core_Transaction> coreTxn = coreRepo.findByTxnRefId(txnId);
		List<TransactionDTO> txnOut = new ArrayList<TransactionDTO>();
		if(coreTxn.isEmpty()){
			throw new ResourceNotFoundException(ErrorCode.TXN_DETAILS_NOT_FOUND);
		}
		for(Core_Transaction txn: coreTxn) {
			TransactionDTO txnDto = TransactionMapper.mapToTransactionDTO(txn,new TransactionDTO());
			txnOut.add(txnDto);
		}
		
		
		return txnOut;
	}
	public CashTransactionResponse getCashtxnDetails(AccountPk accId, String TxnId) {
		CashTransactionResponse response = new CashTransactionResponse();
		Optional<Account> cashAcc = accRepo.findById(accId);
		if(cashAcc.isPresent()) {
			response.setAccNumber(cashAcc.get().getAccount_number());
			response.setAvailableAmt(cashAcc.get().getAvailable_balance());
		}
		List<Core_Transaction> coreTxn = coreRepo.findByTxnRefId(TxnId);
		for(Core_Transaction txn : coreTxn) {
			if(txn.getAccount_type_dr() == 2 || txn.getAccount_type_cr() == 2) {
				Core_Transaction cashTxn = txn;
				response.setTxnAmt(cashTxn.getTxn_amt());
				response.setTxnDesc(cashTxn.getTxn_desc());
				response.setTxnDate(cashTxn.getGen_dt());
			}
		}
		return response;
		
	}

}
