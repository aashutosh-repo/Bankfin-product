package com.fin.bancs.services.si;

import com.fin.bancs.customer.Nominee_Details;

public interface Nominee_Service_Interface {

	Nominee_Details createModifyNomineeDetails(Nominee_Details nominee,int flag);
	void deleteNominee(Nominee_Details nominee_Details);
}
