package com.fin.bancs.mapper;

import com.fin.bancs.dto.CustomerAddressDto;
import com.fin.bancs.dto.CustomerDto;
import com.fin.bancs.dto.DocumentsDtlsDto;

import com.fin.bancs.dto.NomineeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter @Setter
public class RequestWrapper {
	private CustomerDto customerDto;
	private DocumentsDtlsDto docDto;
	private CustomerAddressDto customerAddress;
	private List<NomineeDto> nomineeDetails;
}
