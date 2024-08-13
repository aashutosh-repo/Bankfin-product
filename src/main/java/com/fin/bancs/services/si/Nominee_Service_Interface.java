package com.fin.bancs.services.si;

import java.util.List;

import com.fin.bancs.customer.NomineeDetails;
import com.fin.bancs.dto.NomineeDto;

public interface Nominee_Service_Interface {

	List<NomineeDetails> createModifyNomineeDetails(NomineeDto nominee,int flag);
	void deleteNominee(NomineeDto nominee_Details);
}
