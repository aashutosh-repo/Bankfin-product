package com.fin.bancs.mapper;

import com.fin.bancs.customer.NomineeDetails;
import com.fin.bancs.dto.NomineeDto;

public class NomineeMapper {
	
	public static NomineeDto mpToNomineeDto (NomineeDetails nominee, NomineeDto nomineeDto) {
		nomineeDto.setOwnerId(nominee.getOwnerId());
        nomineeDto.setOwnerType(nominee.getOwnerType());
        nomineeDto.setSeqNum(nominee.getSeqNum());
        nomineeDto.setNomShare(nominee.getNomShare());
        nomineeDto.setNomType(nominee.getNomType());
        nomineeDto.setNomTypeCode(nominee.getNomTypeCode());
        nomineeDto.setNomineeFirstName(nominee.getNomineeFirstName());
        nomineeDto.setNomineeMiddleName(nominee.getNomineeMiddleName());
        nomineeDto.setNomineeLastName(nominee.getNomineeLastName());
        nomineeDto.setRtlnType(nominee.getRtlnType());
        nomineeDto.setRtlnTypeCode(nominee.getRtlnTypeCode());
        nomineeDto.setDateOfBirth(nominee.getDateOfBirth());
        nomineeDto.setNomAddId(nominee.getNomAddId());
        nomineeDto.setNomDocId(nominee.getNomDocId());
        nomineeDto.setNomineeRefNum(nominee.getNomineeRefNum());
        nomineeDto.setVer(nominee.getVer());
        return nomineeDto;
	}
	
	public static NomineeDetails mapToNominee(NomineeDto nomineeDto, NomineeDetails nominee) {
		nominee.setOwnerId(nomineeDto.getOwnerId());
        nominee.setOwnerType(nomineeDto.getOwnerType());
        nominee.setSeqNum(nomineeDto.getSeqNum());
        nominee.setNomShare(nomineeDto.getNomShare());
        nominee.setNomType(nomineeDto.getNomType());
        nominee.setNomTypeCode(nomineeDto.getNomTypeCode());
        nominee.setNomineeFirstName(nomineeDto.getNomineeFirstName());
        nominee.setNomineeMiddleName(nomineeDto.getNomineeMiddleName());
        nominee.setNomineeLastName(nomineeDto.getNomineeLastName());
        nominee.setRtlnType(nomineeDto.getRtlnType());
        nominee.setRtlnTypeCode(nomineeDto.getRtlnTypeCode());
        nominee.setDateOfBirth(nomineeDto.getDateOfBirth());
        nominee.setNomAddId(nomineeDto.getNomAddId());
        nominee.setNomDocId(nomineeDto.getNomDocId());
        nominee.setNomineeRefNum(nomineeDto.getNomineeRefNum());
        nominee.setVer(nomineeDto.getVer());
        return nominee;
	}

}
