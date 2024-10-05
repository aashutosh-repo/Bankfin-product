package com.fin.bancs.services.si;

import java.util.List;
import java.util.Optional;

import com.fin.bancs.customer.NomineeDetails;
import com.fin.bancs.dto.NomineeDto;

public interface Nominee_Service_Interface {
	List<NomineeDetails> createNomineesDetails(List<NomineeDto> nomineeDetails, int flag);
	List<NomineeDetails> modifyNomineeDetails(List<NomineeDto> nominee,int flag);
	void deleteNominee(NomineeDto nominee_Details);
	List<NomineeDetails> findNomineeByOwnerId(int ownerId);
}
