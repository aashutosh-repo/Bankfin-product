package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.fin.bancs.customer.CustomerID;
import com.fin.bancs.customer.Customer_Address_Details;
import com.fin.bancs.customer.Customer_Details;
import com.fin.bancs.customer.DocumentsDetails;
import com.fin.bancs.customer.Nominee_Details;
import com.fin.bancs.dto.CustomerDto;
import com.fin.bancs.dto.DocumentsDtlsDto;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.mapper.CustomerDetailsMapper;
import com.fin.bancs.mapper.DocumentDetailsMapper;
import com.fin.bancs.repository.Customer_Details_Repository;
import com.fin.bancs.repository.DocumentsRepository;
import com.fin.bancs.services.si.Customer_Service_Interface;
import com.fin.bancs.utils.SequenceGenerator;

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
    @Autowired
    private DocumentsRepository docRepository;
    @Autowired
    private SequenceGenerator sequenceGenerator;

    public void CreateCustDetails( CustomerDto inp_cust_details,
    		DocumentsDtlsDto docDtls
//                                   Nominee_Details inp_nominee_details
//                                   Customer_Address_Details inp_Customer_Address_Details
    ){

        Customer_Details customer_Details = new Customer_Details();
        Nominee_Details nominee_Details = new Nominee_Details();
        DocumentsDetails doc = new DocumentsDetails();
        Customer_Address_Details customer_Address_Details = new Customer_Address_Details();
        //Create customer Details
        customer_Details = CustomerDetailsMapper.mapToCustomerDetails(inp_cust_details, new Customer_Details());
        customer_Details.setCustClsngDt(null);
        doc = DocumentDetailsMapper.mapToDocumentDetails(docDtls, new DocumentsDetails());
        
        BigInteger entityId = sequenceGenerator.generateSequence("CustomerID_seq");
	    BigInteger documentId = sequenceGenerator.generateSequence("DocumentID_seq");
	    CustomerID custKey = new CustomerID();
	    custKey.setCustomerID(entityId.intValue());
	    custKey.setCustomerType(2);
	    customer_Details.setCustomerId(custKey);
	    customer_Details= detailsRepository.save(customer_Details);
        
        //Documents Details Creation 
        doc.setDocId(documentId.intValue());
        doc.setCustId(customer_Details.getCustomerId().getCustomerID());
        docRepository.save(doc);
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
