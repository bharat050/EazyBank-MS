package com.microservice.accountService.mapper;

import com.microservice.accountService.dto.CustomerDto;
import com.microservice.accountService.entity.CustomerEntity;

public class CustomerMapper {
	
	public static CustomerEntity mapToEntity(CustomerDto customerDto) {
		return CustomerEntity.builder()
				.name(customerDto.getName())
				.email(customerDto.getEmail())
				.mobileNumber(customerDto.getMobileNumber()).build();
	}
	
	public static CustomerDto mapToDto(CustomerEntity customerEntity) {
		return CustomerDto.builder()
				.email(customerEntity.getEmail())
				.mobileNumber(customerEntity.getMobileNumber())
				.name(customerEntity.getName()).build();
	}
}
