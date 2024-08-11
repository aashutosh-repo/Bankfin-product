package com.fin.bancs.mapper;

import com.fin.bancs.dto.CustomerAddressDto;
import com.fin.bancs.dto.CustomerDto;
import com.fin.bancs.dto.DocumentsDtlsDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestWrapper {
	private CustomerDto customerDto;
	private DocumentsDtlsDto docDto;
	private CustomerAddressDto customerAddress;

}
