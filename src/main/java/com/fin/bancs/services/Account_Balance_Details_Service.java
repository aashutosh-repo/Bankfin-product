package com.fin.bancs.services;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fin.bancs.account.Account;
import com.fin.bancs.account.AccountBalancePk;
import com.fin.bancs.account.AccountPk;
import com.fin.bancs.account.Account_Balance_Details;
import com.fin.bancs.constants.AppConstants;
import com.fin.bancs.constants.TransactionConstants;
import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.error.CustomErrorMessage;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.repository.Account_Balance_Details_Repository;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.repository.Temp_Core_Transaction_Repository;
import com.fin.bancs.services.si.Account_Balance_Interface;
import com.fin.bancs.transactions.CashTransactionInput;
import com.fin.bancs.transactions.Cash_Reserve;
import com.fin.bancs.transactions.Temp_Core_Transaction;
import com.fin.bancs.utils.SequenceGenerator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Account_Balance_Details_Service implements Account_Balance_Interface {

	private static final Logger logger = LogManager.getLogger(AccountBalanceUpdate.class);

	
	private final Account_Balance_Details_Repository balance_Details_Repository;
	private final Temp_Core_Transaction_Repository tempTransactionRepository;
	private final Account_repository accountRepository;
	private final SequenceGenerator seqGenerator;
	private final CoreTransactionServices core_Transaction_services;
	private final Cash_Reserve_Services cash_Reserve_Services;
	


	@Override
	public void creditToAccount(Account accountToCredit, BigDecimal txn_amt) {
		accountToCredit.setAvailable_balance(accountToCredit.getAvailable_balance().add(txn_amt));
		accountRepository.save(accountToCredit);
	}

	@Override
	public void debitFromAccount(Account accountToCredit, BigDecimal txn_amt) {
		accountToCredit.setAvailable_balance(accountToCredit.getAvailable_balance().subtract(txn_amt));
		accountRepository.save(accountToCredit);
	}

	// business rules to validate transaction limits
	@Override
	public boolean checkTransactionLimit(TransactionDTO txn_input) {
		if (txn_input.getTxnType() == TransactionConstants.NEFT && txn_input.getTxnAmt().compareTo(new BigDecimal("200000")) >= 0) {
				throw new CustomErrorMessage(TransactionConstants.TRANSACTION_LIMIT_CROSS);
			}


		if (txn_input.getTxnType() == TransactionConstants.CASH_TRANSACTION && txn_input.getTxnAmt().compareTo(new BigDecimal("50000")) >= 0) {
				throw new CustomErrorMessage(TransactionConstants.TRANSACTION_LIMIT_CROSS);
			}

		logger.info("Transaction Limit Check Completed : Transaction Amount ia as per given LIMIT");
		return true;
	}

	@Override
	public int cashTransactionInitiated(CashTransactionInput txnInput) {
		TransactionDTO txnDto = new TransactionDTO();
		txnDto.setTxnType(txnInput.getTxnType());
		txnDto.setTxnAmt(txnInput.getTxnAmt());
		//Check Transaction Limit for fulfilling Guideline 
		this.checkTransactionLimit(txnDto);
		
		int trnsctnSequence = seqGenerator.generateSequence("AccountBalSequence").intValue();
		Account_Balance_Details account_Balance_Details = new Account_Balance_Details();
		Temp_Core_Transaction temp_Transaction = new Temp_Core_Transaction();
		account_Balance_Details.setAcc_bal_id(new AccountBalancePk(txnInput.getAccountId(),txnInput.getAccountType(),trnsctnSequence));
		logger.info("Finding Account Details of: AccountId: {}, AccountId: {}", txnInput.getAccountId(), txnInput.getAccountId());
		Account acc_cash= accountRepository.findById(new AccountPk(txnInput.getAccountId(),txnInput.getAccountType()))
				.orElseThrow(() -> new CustomErrorMessage(ErrorCode.ACCOUNT_NOT_FOUND));
		account_Balance_Details.setAccountNumber(acc_cash.getAccountNumber());
		temp_Transaction.setTxn_type(txnInput.getTxnType());
		temp_Transaction.setAccount_id(txnInput.getAccountId());
		temp_Transaction.setAccount_type(txnInput.getAccountType());
		temp_Transaction.setCredit_debit_flag(txnInput.getCreditDebitFlag());
		temp_Transaction.setCurrency(txnInput.getCurrency());
		temp_Transaction.setTxn_amt(txnInput.getTxnAmt());
		temp_Transaction.setGst_amt(txnInput.getGstAmt());
		temp_Transaction.setInterest_amt(txnInput.getInterestAmt());
		temp_Transaction.setTxn_desc(txnInput.getTxnDesc());
		temp_Transaction.setTxn_Status(TransactionConstants.TransactionStatus.INITIATED);
		tempTransactionRepository.save(temp_Transaction);
		//Introduce Cash Block here After temporary Transaction getting saved 
		
		Cash_Reserve cash_Reserve = new Cash_Reserve();
		cash_Reserve.setBlockedAmount(temp_Transaction.getTxn_amt());
		cash_Reserve.setBlockReason(temp_Transaction.getTxn_desc());
		cash_Reserve.setLinkedAccountId(acc_cash.getAccountNumber());
		cash_Reserve.setTransactionId(String.valueOf(trnsctnSequence));
		cash_Reserve_Services.createCashReserve(cash_Reserve);
		
		return temp_Transaction.getTxn_id();
		
	}

	@Override
	public void authCashDeposit(int txn_id,int flag) {

		CashTransactionInput txnInput = new CashTransactionInput();
		
		Temp_Core_Transaction tempTxn =  tempTransactionRepository.findById(txn_id).orElseThrow(()->
					new CustomErrorMessage(AppConstants.NOT_FOUND));
		txnInput.setAccountId(tempTxn.getAccount_id());
		txnInput.setAccountType(tempTxn.getAccount_type());
		txnInput.setCreditDebitFlag(tempTxn.getCredit_debit_flag());
		txnInput.setCurrency(tempTxn.getCurrency());
		txnInput.setGstAmt(tempTxn.getGst_amt());
		txnInput.setInterestAmt(tempTxn.getInterest_amt());
		txnInput.setTxnAmt(tempTxn.getTxn_amt());
		txnInput.setTxnDesc(tempTxn.getTxn_desc());
		txnInput.setTxnType(tempTxn.getTxn_type());
		if(flag == 1 ) {
			String transaction_id = core_Transaction_services.cashTransaction(txnInput);
			//After completion of transaction update the Temp transaction Status 
			if(!transaction_id.isBlank()) {
				tempTxn.setTxn_Status(TransactionConstants.TransactionStatus.COMPLETED);
				tempTransactionRepository.save(tempTxn);
			}
		}
	}

}
