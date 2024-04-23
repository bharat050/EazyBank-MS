package com.microservice.accountService.service;

import com.microservice.accountService.dto.CustomerDto;

public interface IAccountService {
	
	public void createAccount(CustomerDto customerDto);
	
	public CustomerDto fetchCustomer(String mobileNo);
	
	public boolean updateCustomerInfo(CustomerDto customerDto);
	
	public void deleteCustomerRecord(int customerId);
}
