package com.fin.bancs.mapper;

import com.fin.bancs.customer.DocumentsDetails;
import com.fin.bancs.dto.DocumentsDtlsDto;

public class DocumentDetailsMapper {
	
	    public static DocumentsDtlsDto mapToDocumentDetailsDto(DocumentsDetails documentDetails,DocumentsDtlsDto documentDetailsDto) {
	        documentDetailsDto.setCustId(documentDetails.getCustId());
	        documentDetailsDto.setDocDescription(documentDetails.getDocDescription());
	        documentDetailsDto.setDocIdentificationNumber(documentDetails.getDocIdentificationNumber());
	        documentDetailsDto.setDocType(documentDetails.getDocType());
	        documentDetailsDto.setDocTypeCode(documentDetails.getDocTypeCode());
	        documentDetailsDto.setIssueDate(documentDetails.getIssueDate());
	        documentDetailsDto.setExpiryDate(documentDetails.getExpiryDate());

	        return documentDetailsDto;
	    }
	    
	    public static DocumentsDetails mapToDocumentDetails(DocumentsDtlsDto documentDetailsDto , DocumentsDetails documentDetails) {
	        documentDetails.setCustId(documentDetailsDto.getCustId());
	        documentDetails.setDocDescription(documentDetailsDto.getDocDescription());
	        documentDetails.setDocIdentificationNumber(documentDetailsDto.getDocIdentificationNumber());
	        documentDetails.setDocType(documentDetailsDto.getDocType());
	        documentDetails.setDocTypeCode(documentDetailsDto.getDocTypeCode());
	        documentDetails.setIssueDate(documentDetailsDto.getIssueDate());
	        documentDetails.setExpiryDate(documentDetailsDto.getExpiryDate());
	        return documentDetails;
	    }


}
