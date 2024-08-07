package com.fin.bancs.services.si;

import com.fin.bancs.customer.Customer_Details;

public interface Customer_Service_Interface {
	
	Customer_Details findCustomer(String mobile);

}
