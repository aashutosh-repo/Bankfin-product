package com.fin.bancs.controller;

import com.fin.bancs.BP.CustomerID;
import com.fin.bancs.BP.Customer_Details;
import com.fin.bancs.BP.Nominee_Details;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ErrorHandler;
import com.fin.bancs.error.GlobalException;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.repository.Nominee_Repository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class Nominee_Details_Controller {

    @Autowired
    private Nominee_Repository nomineeRepository;


	public Nominee_Details createModifyNomineeDetails(Nominee_Details nomineeDetails,int flag){
		//Flag=1-> Create
		//flag=2 -> Modify
		if(flag==2) {
			//check if customer exist then proceed else throw Error "Customer Not Exist"
			//need a methd t check the customer or in input required cust_id
		}
		
		Nominee_Details nominee_details = new Nominee_Details();
		if(flag==1) {
		nominee_details = nomineeRepository.save(nomineeDetails);
		Customer_Details customer= new Customer_Details();
		CustomerID custPk= new CustomerID();
		custPk.setCUS_ID(nomineeDetails.getOwner_id());
		}else {
			Nominee_Details nominee_detail = nomineeRepository.save(nominee_details);
		}
        Optional<Nominee_Details> nominee = nomineeRepository.findById(nominee_details.getNominee_id());
        if(nominee.isEmpty()){
			throw new ResourceNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND);
		}
		return nominee_details;
    }


}
