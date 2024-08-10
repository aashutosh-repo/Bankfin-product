package com.fin.bancs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.fin.bancs.customer.Customer_Address_Details;
import com.fin.bancs.customer.Customer_Details;
import com.fin.bancs.customer.Nominee_Details;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.repository.Customer_Details_Repository;
import com.fin.bancs.services.si.Customer_Service_Interface;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Customer_Details_services implements Customer_Service_Interface{
	
	@InitBinder
	public void initBinder(WebDataBinder webdataBinder) {
		//This method is to trim all spaces from start and end of string 
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webdataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

    @Autowired
    private Customer_Details_Repository detailsRepository;

    public void CreateCustDetails( Customer_Details inp_cust_details
//                                   Nominee_Details inp_nominee_details
//                                   Customer_Address_Details inp_Customer_Address_Details
    ){

        Customer_Details customer_Details = new Customer_Details();
        Nominee_Details nominee_Details = new Nominee_Details();
        Customer_Address_Details customer_Address_Details = new Customer_Address_Details();

        //These three value should not came as a Input from customer It should Generate Automatically
        int cust_id=0; //Add code to generate Unique Primary key
        int nominee_id= 0; ///Add code to generate Unique Primary key
        int address_id =0; ///Add code to generate Unique Primary key
        int nom_doc_id=0;

        //Create customer Details

        customer_Details.setFIRST_NAME(inp_cust_details.getFIRST_NAME());
        customer_Details.setLAST_NAME(inp_cust_details.getLAST_NAME());
        customer_Details.setFATHER_NAME(inp_cust_details.getFATHER_NAME());
        customer_Details.setMOTHER_NAME(inp_cust_details.getMOTHER_NAME());
        customer_Details.setEMAIL(inp_cust_details.getEMAIL());
        //customer_Details.setMOBILE_NUMBER(inp_cust_details.getMOBILE_NUMBER());
        customer_Details.setSTATUS(inp_cust_details.getSTATUS());
        customer_Details.setADDRESS_ID(address_id); //Review
        customer_Details.setNOMINEE_ID(nominee_id);
        customer_Details.setCUST_CREATION_DT(inp_cust_details.getCUST_CREATION_DT());
        customer_Details.setDATE_OF_BIRTH(inp_cust_details.getDATE_OF_BIRTH());
        customer_Details.setRATING_AGENCY(inp_cust_details.getRATING_AGENCY());
        customer_Details.setRISK_RATING(inp_cust_details.getRISK_RATING());
        detailsRepository.save(customer_Details);
		//create Nominee details
//		Nominee_Details nominee_detail=
//				nomineeDetailsController.createNomineeDetails(inp_nominee_details);
//
		//Create Address details
//		Customer_Address_Details cust_add_dtls =
//				customer_Address_Details.Cr(inp_Customer_Address_Details);

    }
    public void DeleteCustomer(Customer_Details customerDetails){
//        CustomerID customerID= new CustomerID(customerDetails.getCUS_ID(),customerDetails.CUS_TYP);
//		Customer_Details cust_dtls = entityManager.find(Customer_Details.class,customerID);
//		cust_dtls.setCUST_CLSNG_DT(customerDetails.getCUST_CLSNG_DT());
//		cust_dtls.setSTATUS(0000); //put Account Closing Status
    }
    public List<Customer_Details> getAllCust(){
        List<Customer_Details> allcust = detailsRepository.findAll();
        return allcust;
    }
	@Override
	public Customer_Details findCustomer(String mobile) {
		Optional<Customer_Details> customerDtls = detailsRepository.findByMobileNumber(mobile);
		if(customerDtls.isEmpty()) {
			throw new ResourceNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND);
		}
		return customerDtls.get();
	}
}
