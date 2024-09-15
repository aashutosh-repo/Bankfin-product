package com.fin.bancs.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fin.bancs.account.Account;
import com.fin.bancs.account.LineOfCredit;
import com.fin.bancs.constants.AccountsConstants;
import com.fin.bancs.constants.TradeFinanceConstants;
import com.fin.bancs.dto.TransactionDTO;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.repository.Account_repository;
import com.fin.bancs.repository.LIneOfCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.bancs.repository.ShipmentRepository;
import com.fin.bancs.repository.TFRepository;
import com.fin.bancs.trade.Shipment;
import com.fin.bancs.trade.ShipmentID;
import com.fin.bancs.trade.TradeFinance;
import com.fin.bancs.utils.SequenceGenerator;


@Service
public class TradeServices {


	@Autowired
	TFRepository tradefinRepo;
	@Autowired
	ShipmentRepository shipmentrepo;
	@Autowired
	private LIneOfCreditRepository locRepository;
	@Autowired
	private Account_repository accRepository;
	@Autowired
	private Core_Transaction_services coreTransaction;
	@Autowired
	private SequenceGenerator sequenceGenerator;

	public boolean isPaymentCompleted(String ShipmentId) {
		Optional<TradeFinance> tf  = tradefinRepo.findById(ShipmentId); //WRONG
        return tf.map(tradeFinance -> tradeFinance.getTradeStatus().equalsIgnoreCase("COMPLETED")).orElse(false);
    }
	public void createTrade(TradeFinance tradef, Shipment senderShipmentDtls, Shipment receiverShipmentDtls) {
		BigInteger tradeId = sequenceGenerator.generateSequence("TradeId_seq");
		BigInteger shipmentId = sequenceGenerator.generateSequence("shipmentId_seq");
		tradef.setContractId(String.valueOf(tradeId));
		tradefinRepo.save(tradef);
		ShipmentID shipId= new ShipmentID();
		shipId.setShipmentId(shipmentId.toString());
		shipId.setShipmentType(TradeFinanceConstants.SHIPMENT_TYPE_SEA);
		senderShipmentDtls.setContractId(String.valueOf(tradeId));
		senderShipmentDtls.setShipmentId(shipId);
		shipmentrepo.save(senderShipmentDtls);
		shipId.setShipmentType(TradeFinanceConstants.SHIPMENT_TYPE_SEA);
		receiverShipmentDtls.setShipmentId(shipId);
		receiverShipmentDtls.setContractId(String.valueOf(tradeId));
		shipmentrepo.save(receiverShipmentDtls);

	}

	public void verifyShipmentStatus(String contractId) {
		TradeFinance tradeFinance = getTradeFinance(contractId);
		if (tradeFinance != null) {
			Optional<Shipment> shipment  = shipmentrepo.findByContractId(contractId);
			if (shipment.get().getShipmentStatus().equals(TradeFinanceConstants.WORKFLOW_COMPLETED)) {
				Optional<LineOfCredit> lineOfCredit = locRepository.findByShipmentId(shipment.get().getShipmentId().getShipmentId());
				initiatePayment(lineOfCredit.get());
			} else {
				System.out.println("Shipment status is not completed");
			}
		} else {
			System.out.println("Contract not found");
		}
	}

	private void initiatePayment(LineOfCredit loc) {

		if(loc.getLocStatus().equalsIgnoreCase("ACTIVE")) {

			//Write Transaction
			TransactionDTO txndto = new TransactionDTO();
			List<TransactionDTO> ListTxnDto = new ArrayList<>();
            new Account();
            Account expAccount;
			Account importerAccount;

			expAccount = (Account) accRepository.findByCustId(Integer.parseInt(loc.getExporterId()));
			importerAccount = (Account) accRepository.findByCustId(Integer.parseInt(loc.getImporterId()));

			txndto.setAccountIdCr(expAccount.getAccountId().getAccount_id());
			txndto.setAccountIdDr(importerAccount.getAccountId().getAccount_id());
			txndto.setTxnAmt(loc.getCreditAmout());
			txndto.setAccountTypeDr(AccountsConstants.TRADE_ACCOUNT);
			txndto.setAccountTypeCr(AccountsConstants.TRADE_ACCOUNT);
			txndto.setTxnDesc("TF : Credit Transfer ");
			txndto.setCurrency(loc.getCurrency());
			ListTxnDto.add(txndto);
			coreTransaction.CreateA2ATransaction(ListTxnDto);

			loc.setPymntStatus(TradeFinanceConstants.PYMNT_FULLPAID);
			loc.setCreditAmout(new BigDecimal(0));
			locRepository.save(loc);



		}else {
			System.out.println("Not Active");
		}


	}
	private TradeFinance getTradeFinance(String contractId) {
		Optional<Shipment> shipment  = shipmentrepo.findByContractId(contractId);
		if(shipment.isPresent()) {
			Optional<TradeFinance> trade = tradefinRepo.findById(shipment.get().getContractId());
			if(trade.isPresent()){
				return trade.get();
			}else {
				throw new ResourceNotFoundException(ErrorCode.TRADE_DETAILS_NOT_FOUND+" : " + contractId);
			}
		}
		return null;

	}

}
