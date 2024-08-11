package com.fin.bancs.mapper;

import com.fin.bancs.customer.CustomerDetails;
import com.fin.bancs.dto.CustomerDto;

public class CustomerDetailsMapper {
	
	public static CustomerDto mapToCustomerDetailsDto( CustomerDetails customerDetails,CustomerDto customerDetailsDto) {
		customerDetailsDto.setFirstName(customerDetails.getFirstName());
        customerDetailsDto.setLastName(customerDetails.getLastName());
        customerDetailsDto.setFatherName(customerDetails.getFatherName());
        customerDetailsDto.setMotherName(customerDetails.getLastName());
        customerDetailsDto.setMail(customerDetails.getEmail());
        customerDetailsDto.setMobileNumber(customerDetails.getMobileNumber());
        customerDetailsDto.setStatus(customerDetails.getStatus());
        customerDetailsDto.setDateOfBirth(customerDetails.getDateOfBirth());
        customerDetailsDto.setCustClsngDt(customerDetails.getCustClsngDt());
        customerDetailsDto.setRiskProfile(customerDetails.getRiskProfile());
        customerDetailsDto.setRatingAgency(customerDetails.getRatingAgency());
        return customerDetailsDto;
	}
	public static CustomerDetails mapToCustomerDetails(CustomerDto customerDetailsDto, CustomerDetails customerDetails)
	{
		customerDetails.setFirstName(customerDetailsDto.getFirstName());
        customerDetails.setLastName(customerDetailsDto.getLastName());
        customerDetails.setFatherName(customerDetailsDto.getFatherName());
        customerDetails.setMotherName(customerDetailsDto.getLastName());
        customerDetails.setEmail(customerDetailsDto.getMail());
        customerDetails.setMobileNumber(customerDetailsDto.getMobileNumber());
        customerDetails.setStatus(customerDetailsDto.getStatus());
        customerDetails.setDateOfBirth(customerDetailsDto.getDateOfBirth());
        customerDetails.setCustClsngDt(customerDetailsDto.getCustClsngDt());
        customerDetails.setRiskProfile(customerDetailsDto.getRiskProfile());
        customerDetails.setRatingAgency(customerDetailsDto.getRatingAgency());
        
        return customerDetails;
	}
	

}
