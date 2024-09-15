package com.fin.bancs.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fin.bancs.constants.AccountsConstants;
import com.fin.bancs.error.ErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fin.bancs.account.Account;
import com.fin.bancs.account.AccountPk;
import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.mapper.TransactionMapper;
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




    public void CreateA2ATransaction(List<TransactionDTO> txnInputDTO) {
		BigInteger txnId = seqGen.generateSequence("TxnSeq");
		String txnRef = "2-" + txnId.toString();
		Core_Transaction core_transaction_cr= new Core_Transaction();
		Core_Transaction core_transaction_dr= new Core_Transaction();
		List<Core_Transaction> core_transaction = new ArrayList<>();
		List<Account> UpdateAccBal= new ArrayList<>();

		for(TransactionDTO txn : txnInputDTO) {
			Core_Transaction coreTxn = TransactionMapper.mapTOCoreTxnLayer(txn,new Core_Transaction());
			if(coreTxn.getCredit_debit_flag() == 1) //Credit Account
			{
				core_transaction_cr = coreTxn;
			}
			if(coreTxn.getCredit_debit_flag() == 2) //Debit Account
			{
				core_transaction_dr = coreTxn;
			}
		}
		  //Transfer-> Do transfer Operation
			// total 4 transaction row will be written
			//1. Debit from source account 2. credit to internal Credit Account
			//3. Credit to source account 4. Debit from internal Debit Account
			Core_Transaction coreTransactionLayer_int_cr = new Core_Transaction();
			Core_Transaction coreTransactionLayer_int_dr = new Core_Transaction();
			Core_Transaction coreTransaction_acc_cr = new Core_Transaction();
			Core_Transaction coreTransaction_acc_dr = new Core_Transaction();
			//case1 -- Debit from source account
			AccountPk account_pk = new AccountPk();
			Account account_dr;
			Account account_cr;
			Account internal_acc_cr = new Account();
			Account internal_acc_dr;
			if(!Objects.equals(core_transaction_cr.getTxn_amt(), core_transaction_dr.getTxn_amt())){
				throw  new ErrorHandler("Debit Amount and Credit Amount should be Same");
			}
			BigDecimal txnAmt = core_transaction_cr.getTxn_amt();
			//Account balance Update Starts here
		if(core_transaction_cr.getCredit_debit_flag()==1) {
			account_pk.setAccount_id(core_transaction_cr.getAccount_id_cr()); //CR type Inter
			account_pk.setAccount_type(core_transaction_cr.getAccount_type_cr());
			Optional<Account> account_opt_cr = accRepo.findById(account_pk);
			account_pk.setAccount_id(123456); //Default internal debit account
			account_pk.setAccount_type(AccountsConstants.INTERNAL_ACCOUNT);
			Optional<Account> int_acc_dr = accRepo.findById(account_pk);
			if (account_opt_cr.isPresent() && int_acc_dr.isPresent()) {
				account_cr = account_opt_cr.get();
				internal_acc_dr = int_acc_dr.get();
				account_cr.setAvailable_balance(account_cr.getAvailable_balance().subtract(core_transaction_dr.getTxn_amt()));
				UpdateAccBal.add(account_cr);
				internal_acc_cr.setAvailable_balance(internal_acc_dr.getAvailable_balance().add(core_transaction_dr.getTxn_amt()));
				UpdateAccBal.add(internal_acc_cr);
			} else {
				throw new ResourceNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND);
			}
			//Account1 balance Update Ends here
			coreTransaction_acc_cr.setTxn_desc(core_transaction_cr.getTxn_desc() + " Account Transfer : Credit");
			coreTransaction_acc_cr.setAccount_id_cr(account_cr.getAccountId().getAccount_id());
			coreTransaction_acc_cr.setAccount_type_cr(account_cr.getAccountId().getAccount_type());
			coreTransaction_acc_cr.setCredit_debit_flag(1);
			coreTransaction_acc_cr.setTxn_amt(txnAmt);
			coreTransaction_acc_cr.setGen_dt(LocalDate.now());
			coreTransaction_acc_cr.setCurrency(core_transaction_cr.getCurrency());
			coreTransaction_acc_cr.setTxn_seq(1);
			coreTransaction_acc_cr.setTxnRefId(txnRef);

			//case2 -- Debit From internal Account
			coreTransactionLayer_int_dr.setTxn_amt(txnAmt);
			coreTransactionLayer_int_dr.setGen_dt(LocalDate.now());
			coreTransactionLayer_int_dr.setTxn_desc(core_transaction_dr.getTxn_desc() + " A2A internal : Debit");
			coreTransactionLayer_int_dr.setCredit_debit_flag(2);
			coreTransactionLayer_int_dr.setTxn_seq(2);
			coreTransactionLayer_int_dr.setCurrency(core_transaction_cr.getCurrency());

			core_transaction.add(coreTransaction_acc_cr);
			core_transaction.add(coreTransactionLayer_int_dr);
		}
		
		if(core_transaction_dr.getCredit_debit_flag() == 2) {
			account_pk.setAccount_id(core_transaction_dr.getAccount_id_cr()); 
			account_pk.setAccount_type(core_transaction_dr.getAccount_type_cr());
			Optional<Account> account_opt_dr = accRepo.findById(account_pk);
			account_pk.setAccount_id(123455);
			account_pk.setAccount_type(AccountsConstants.INTERNAL_ACCOUNT);
			Optional<Account> int_acc_cr = accRepo.findById(account_pk);
			if (account_opt_dr.isPresent() && int_acc_cr.isPresent() ) {
				account_dr = account_opt_dr.get();
				internal_acc_cr = int_acc_cr.get();
				account_dr.setAvailable_balance(account_dr.getAvailable_balance().subtract(txnAmt));
				UpdateAccBal.add(account_dr);
				internal_acc_cr.setAvailable_balance(internal_acc_cr.getAvailable_balance().add(txnAmt));
				UpdateAccBal.add(internal_acc_cr);
			}else {
				throw new ResourceNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND);
			}
			//Account2 balance Update Ends here

			coreTransaction_acc_dr.setTxn_desc(core_transaction_cr.getTxn_desc() + " Account Transfer : Debit");
			coreTransaction_acc_dr.setAccount_id_cr(account_dr.getAccountId().getAccount_id());
			coreTransaction_acc_dr.setAccount_type_cr(account_dr.getAccountId().getAccount_type());
			coreTransaction_acc_dr.setCredit_debit_flag(2);
			coreTransaction_acc_dr.setTxn_amt(txnAmt);
			coreTransaction_acc_dr.setGen_dt(LocalDate.now());
			coreTransaction_acc_dr.setCurrency("INR");
			coreTransaction_acc_dr.setTxn_seq(3);
			coreTransaction_acc_dr.setTxnRefId(txnRef);
			
				//case2 -- Credit To internal Account
				coreTransactionLayer_int_cr.setTxn_amt(txnAmt);
				coreTransactionLayer_int_cr.setGen_dt(LocalDate.now());
				coreTransactionLayer_int_cr.setTxn_desc(core_transaction_dr.getTxn_desc() + " A2A internal : Credit");
				coreTransactionLayer_int_cr.setCredit_debit_flag(1);
				coreTransactionLayer_int_cr.setTxn_seq(4);
				coreTransactionLayer_int_cr.setCurrency("INR");
				
			core_transaction.add(coreTransaction_acc_dr);
			core_transaction.add(coreTransactionLayer_int_cr);
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
			Account acc;
			Optional<Account> acc_internal;
			Optional<Account> acc_cash;
			AccountPk accPk= new AccountPk();
			AccountPk accID= new AccountPk();
			BigDecimal total_amt_internal;
			BigDecimal total_amt_cash;
			//debit/credit to internal account srarts
			if(txnInput.getCreditDebitFlag() == 2) {
				//accPk.setAccount_id(core_transaction_cash.getAccount_id_cr()); //internal Account need to maintain internally
				accID.setAccount_id(111111122); //Default credit Account
				accID.setAccount_type(AccountsConstants.INTERNAL_ACCOUNT); //For inernal account
			}else {
				accID.setAccount_id(111111123); //Default debit Account
				accID.setAccount_type(AccountsConstants.INTERNAL_ACCOUNT); //For inernal account
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
			response.setAccNumber(cashAcc.get().getAccountNumber());
			response.setAvailableAmt(cashAcc.get().getAvailable_balance());
		}
		List<Core_Transaction> coreTxn = coreRepo.findByTxnRefId(TxnId);
		for(Core_Transaction txn : coreTxn) {
			if(txn.getAccount_type_dr().equals(AccountsConstants.SAVINGS) || txn.getAccount_type_cr().equals(AccountsConstants.SAVINGS)) {
				Core_Transaction cashTxn = txn;
				response.setTxnAmt(cashTxn.getTxn_amt());
				response.setTxnDesc(cashTxn.getTxn_desc());
				response.setTxnDate(cashTxn.getGen_dt());
			}
		}
		return response;
		
	}

}
