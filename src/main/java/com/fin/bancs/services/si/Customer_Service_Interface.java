package com.fin.bancs.services.si;

import com.fin.bancs.customer.CustomerDetails;
import com.fin.bancs.dto.CustomerDto;
import com.fin.bancs.dto.DocumentsDtlsDto;
import com.fin.bancs.dto.NomineeDto;

import java.util.List;
import java.util.Optional;

public interface Customer_Service_Interface {

	public void CreateCustDetails(CustomerDto inp_cust_details,
								  DocumentsDtlsDto docDtls,List<NomineeDto> nomineeDtoList);
	CustomerDto findCustomerByMobileNumber(String mobile);
	CustomerDto modifyCustomer(CustomerDto cuatomerInp, int CustomerId,int CustomerType);
	List<CustomerDto> getAllCust();

}
