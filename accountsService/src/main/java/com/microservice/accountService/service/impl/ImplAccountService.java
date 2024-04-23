package com.microservice.accountService.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservice.accountService.constants.AccountConstant;
import com.microservice.accountService.dto.AccountsDto;
import com.microservice.accountService.dto.CustomerDto;
import com.microservice.accountService.entity.AccountsEntity;
import com.microservice.accountService.entity.CustomerEntity;
import com.microservice.accountService.exception.CustomerIsAlreadyExistsException;
import com.microservice.accountService.exception.ResourceNotFoundException;
import com.microservice.accountService.mapper.AccountMapper;
import com.microservice.accountService.mapper.CustomerMapper;
import com.microservice.accountService.repository.AccountsRepository;
import com.microservice.accountService.repository.CustomerRepository;
import com.microservice.accountService.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImplAccountService implements IAccountService{

	AccountsRepository accountsRepository;
	CustomerRepository customerRepository;
	
	public void createAccount(CustomerDto customerDto) {
		CustomerEntity entity = CustomerMapper.mapToEntity(customerDto);
		
		Optional<CustomerEntity> checkEntity = customerRepository.findByMobileNumber(entity.getMobileNumber());
		
		if(checkEntity.isPresent()) {
			throw new CustomerIsAlreadyExistsException(
					"Entered Mobile Number is already existed. Please try with another number"
					+ "mobile no is" + checkEntity.get().getMobileNumber());
		}
		
		CustomerEntity savEntity = customerRepository.save(entity);
		
		accountsRepository.save(createBankAccount(savEntity));
	}
	
	public AccountsEntity createBankAccount(CustomerEntity entity) {
		
		AccountsEntity accountsEntity = AccountsEntity.builder()
				.customerId(entity.getCustomerId())
				.accountType(AccountConstant.SAVINGS)
				.branchAddress(AccountConstant.ADDRESS)
				.build();
		
		
		return accountsEntity;
	}

	@Override
	public CustomerDto fetchCustomer(String mobileNo) {
		
		CustomerEntity entity = customerRepository.findByMobileNumber(mobileNo).orElseThrow(
				()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNo));
		System.out.println(entity.toString());
		
		AccountsEntity accountsEntity = accountsRepository.findByCustomerId(entity.getCustomerId()).orElseThrow(
				()-> new ResourceNotFoundException("Accounts", "customerId", Integer.toString(entity.getCustomerId())));
		
		CustomerDto customerDto = CustomerMapper.mapToDto(entity);
		customerDto.setAccountsDto(AccountMapper.mapToDto(accountsEntity));
		return customerDto;
	}

	@Override
	public boolean updateCustomerInfo(CustomerDto customerDto) {
		
		boolean isUpdated = false;
		
		AccountsDto accountsDto = customerDto.getAccountsDto();
		
		if(accountsDto!=null) {
			AccountsEntity accountsEntity = AccountMapper.mapToAccount(accountsDto);
			
			AccountsEntity newAccountsEntity = accountsRepository.findById(accountsDto.getAccountNo()).orElseThrow(
					()-> new ResourceNotFoundException("Account", "AccValues", "Incorrect Info"));
			newAccountsEntity.setAccountType(accountsEntity.getAccountType());
			newAccountsEntity.setBranchAddress(accountsDto.getBranchAddress());
			
			accountsRepository.save(newAccountsEntity);
			
		 	CustomerEntity customerEntity = customerRepository.findById(newAccountsEntity.getCustomerId()).orElseThrow(
		 			() -> new ResourceNotFoundException("Customer", "CustomerValues", "Incorrect Info"));
		 	
		 	CustomerEntity newEntity = CustomerMapper.mapToEntity(customerDto);
		 	customerEntity.setEmail(newEntity.getEmail());
		 	customerEntity.setMobileNumber(newEntity.getMobileNumber());
		 	customerEntity.setName(newEntity.getName());
		 	
			customerRepository.save(customerEntity);
			
			isUpdated = true;
		 	
		}else {
			throw new ResourceNotFoundException("New Account", "Info", "Please Fill Data Correctly");
		}
		
		
		
		
		return isUpdated;
	}

	@Override
	public void deleteCustomerRecord(int customerId) {

		CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow( ()-> 
		new ResourceNotFoundException("Customer", "record with ID", Integer.toString(customerId)));
		
		customerRepository.delete(customerEntity);
		
//		AccountsEntity accountsEntity = accountsRepository.findByCustomerId(customerId).orElseThrow( ()-> 
//		new ResourceNotFoundException("Account", "record with ID", Integer.toString(customerId)));
		if(accountsRepository.findByCustomerId(customerId).isEmpty()) {
			throw new ResourceNotFoundException("Account", "record with ID", Integer.toString(customerId));
		}
		accountsRepository.deleteByCustomerId(customerId);
		
	}

}
