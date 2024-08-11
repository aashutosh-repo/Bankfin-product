package com.fin.bancs.mapper;

import com.fin.bancs.customer.CustomerAddressDetails;
import com.fin.bancs.dto.CustomerAddressDto;

public class AddressMapper {
	
	public static CustomerAddressDto mapToCustomerAddressDto(CustomerAddressDetails addressDetails,CustomerAddressDto addressDtlsDto) {
		addressDtlsDto.setAddressType(addressDetails.getAddressType());
        addressDtlsDto.setAddressLn1(addressDetails.getAddressLn1());
        addressDtlsDto.setAddressLn2(addressDetails.getAddressLn2());
        addressDtlsDto.setCity(addressDetails.getCity());
        addressDtlsDto.setVillage(addressDetails.getVillage());
        addressDtlsDto.setDistrict(addressDetails.getDistrict());
        addressDtlsDto.setTaluka(addressDetails.getTaluka());
        addressDtlsDto.setState(addressDetails.getState());
        addressDtlsDto.setLastUpdate(addressDetails.getLastUpdate());
        addressDtlsDto.setDateOfCapture(addressDetails.getDateOfCapture());
        return addressDtlsDto;
	}
	
	public static CustomerAddressDetails mapToCustomerAddress(CustomerAddressDto addressDtlsDto, CustomerAddressDetails addressDtls) {
		addressDtls.setAddressType(addressDtlsDto.getAddressType());
        addressDtls.setAddressLn1(addressDtlsDto.getAddressLn1());
        addressDtls.setAddressLn2(addressDtlsDto.getAddressLn2());
        addressDtls.setCity(addressDtlsDto.getCity());
        addressDtls.setVillage(addressDtlsDto.getVillage());
        addressDtls.setDistrict(addressDtlsDto.getDistrict());
        addressDtls.setTaluka(addressDtlsDto.getTaluka());
        addressDtls.setState(addressDtlsDto.getState());
        addressDtls.setLastUpdate(addressDtlsDto.getLastUpdate());
        addressDtls.setDateOfCapture(addressDtlsDto.getDateOfCapture());
        
        return addressDtls;
	}

}
