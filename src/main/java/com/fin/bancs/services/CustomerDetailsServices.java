package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fin.bancs.dto.NomineeDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.fin.bancs.customer.CustomerID;
import com.fin.bancs.customer.CustomerDetails;
import com.fin.bancs.customer.DocumentsDetails;
import com.fin.bancs.customer.NomineeDetails;
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
import org.springframework.cache.annotation.CachePut;

import jakarta.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerDetailsServices implements Customer_Service_Interface{

	@InitBinder
	public void initBinder(WebDataBinder webdataBinder) {
		//This method is to trim all spaces from start and end of string 
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webdataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	private final Customer_Details_Repository detailsRepository;
	private final DocumentsRepository docRepository;
	private final NomineeDetailsServices nomineeService;
	private final SequenceGenerator sequenceGenerator;

	@Override
	@CacheEvict(value = "customers", key = "'allCustomers'", allEntries = false)
	public void CreateCustDetails( CustomerDto inp_cust_details,
								   DocumentsDtlsDto documentsDtlsDto,List<NomineeDto> nomineeDtoList){

        new CustomerDetails();
        CustomerDetails customer_Details;
        new NomineeDetails();
        new DocumentsDetails();
        DocumentsDetails doc;
		//Create customer Details
		customer_Details = CustomerDetailsMapper.mapToCustomerDetails(inp_cust_details, new CustomerDetails());
		customer_Details.setCustClsngDt(null);
		doc = DocumentDetailsMapper.mapToDocumentDetails(documentsDtlsDto, new DocumentsDetails());

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
		nomineeService.createNomineesDetails(nomineeDtoList,1);
	}

	@Cacheable(value = "customers", key = "'allCustomers'")
	@Override
	public List<CustomerDto> getAllCust(){
		List<CustomerDetails> allcust = detailsRepository.findAll();
		List<CustomerDto> allCustDtoOut = new ArrayList<>();
		for(CustomerDetails cust : allcust) {
			CustomerDto customerDetailsSingle = CustomerDetailsMapper.mapToCustomerDetailsDto(cust, new CustomerDto());
			allCustDtoOut.add(customerDetailsSingle);
		}

		return allCustDtoOut;
	}
	@Cacheable(value = "customers", key = "#mobileNumber")
	@Override
	public CustomerDto findCustomerByMobileNumber(String mobileNumber) {
		Optional<CustomerDetails> customerDetails = detailsRepository.findByMobileNumber(mobileNumber);
		if(customerDetails.isEmpty()){
			throw new ResourceNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND + " Customer MobileNumber :"+ mobileNumber);
		}
        return CustomerDetailsMapper.mapToCustomerDetailsDto(customerDetails.get(), new CustomerDto());
	}

	@CachePut(value = "customers", key = "#customerInp.mobileNumber")
	@Override
	public CustomerDto modifyCustomer(CustomerDto customerInp, int customerId,int customerType) {
		CustomerID cusPkey = new CustomerID();
		CustomerDto cDto = new CustomerDto();
		CustomerDetails customer = new CustomerDetails();
		cusPkey.setCustomerID(customerId);
		cusPkey.setCustomerType(customerType);
		Optional<CustomerDetails> customerDetails = detailsRepository.findById(cusPkey);
		if(customerDetails.isPresent()) {
            CustomerDetailsMapper.mapToCustomerDetails(customerInp, customer);
            customer = detailsRepository.save(customer);
			cDto = CustomerDetailsMapper.mapToCustomerDetailsDto(customer, new CustomerDto());
		}
		return cDto;
	}
}
