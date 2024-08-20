package com.fin.bancs.services.si;

import com.fin.bancs.customer.CustomerDetails;
import com.fin.bancs.dto.CustomerDto;

public interface Customer_Service_Interface {

	CustomerDto findCustomerByMobileNumber(String mobile);

}
